package com.sk.coe.swlab.techhub.domain.board;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.coe.swlab.techhub.controller.board.dto.BoardDTO;
import com.sk.coe.swlab.techhub.domain.message.ResponseMessage;

/**
 * https://www.baeldung.com/hibernate-criteria-queries
 * https://www.baeldung.com/jpa-pagination
 */
@Service
public class BoardService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    private BoardRepository repository;

    public ResponseMessage<BoardDTO> writeBoard(Board board) {
        final Board boardData = repository.save(board);
        return ResponseMessage.<BoardDTO>builder()
                .success(true)
                .body(boardMapper.toDTO(boardData))
                .build();
    }

    public ResponseMessage deleteBoardById(Long boardId) {
        boolean success = true;
        try {
            repository.deleteById(boardId);
        } catch (Exception e) {
            success = false;
        } finally {
            return ResponseMessage.builder().success(success).build();
        }
    }

    @Transactional
    public ResponseMessage<BoardDTO> updateBoard(Board board) {
        Board targetData = setModifiedDataToBoard(getBoardInfoById(board.getId()), board);;

        final Board savedData = repository.save(targetData);
        return ResponseMessage.<BoardDTO>builder()
                .success(true)
                .body(boardMapper.toDTO(savedData))
                .build()
                ;
    }


    @Transactional
    public ResponseMessage<BoardDTO> findBoardById(Long boardId) {
        Board boardData = getBoardInfoById(boardId);
        boardData.setViewCount(boardData.getViewCount() + 1);

        final Board savedData = repository.save(boardData);

        return ResponseMessage.<BoardDTO>builder()
                .success(true)
                .body(boardMapper.toDTO(savedData))
                .build()
                ;
    }

    public ResponseMessage<Page<BoardDTO>> findAllBoard(Pageable pageInfo) {
        final Page<Board> all = repository.findAll(pageInfo);

        final Page<BoardDTO> boardDTOS = new PageImpl<BoardDTO>(boardMapper.toDTO(all.getContent()), pageInfo, all.getTotalElements());
        return ResponseMessage.<Page<BoardDTO>>builder()
                .success(true)
                .body(boardDTOS)
                .build()
                ;
    }

    public ResponseMessage<Page<BoardDTO>> findAllBoardByCategoryId(String category, Pageable pageable) {

        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Board> cr = cb.createQuery(Board.class);
        final Root<Board> root = cr.from(Board.class);

        final CriteriaQuery<Board> criteriaQuery = cr.select(root).where(cb.equal(root.get("category"), category));
        criteriaQuery.orderBy(cb.desc(root.get("id")));

        final TypedQuery<Board> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        final Long totalCount = getTotalBoardCountByCategoryId(category);

        final List<Board> resultList = query.getResultList();
        final Page<BoardDTO> boardDTOS = new PageImpl<BoardDTO>(boardMapper.toDTO(resultList), pageable, totalCount);

        return ResponseMessage.<Page<BoardDTO>>builder()
                .success(true)
                .body(boardDTOS)
                .build()
                ;
    }

    public Long getTotalBoardCountByCategoryId(String category) {

        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> cr = cb.createQuery(Long.class);

        final Root<Board> root = cr.from(Board.class);
        cr.where(cb.equal(root.get("category"), category));

        cr.select(cb.count(root));
        final TypedQuery<Long> query = entityManager.createQuery(cr);
        return query.getSingleResult();
    }


    private Board getBoardInfoById(Long boardId) {
        final Optional<Board> boardData = repository.findById(boardId);
        return boardData.orElseThrow(() -> new RuntimeException(String.format("There is not board data by id : [%s]", boardId)));
    }

    private Board setModifiedDataToBoard(Board targetBoard, Board board) {
        if (board.getBoardType() != null) {
            targetBoard.setBoardType(board.getBoardType());
        }

        if (board.getCategory() != null) {
            targetBoard.setCategory(board.getCategory());
        }

        if (board.getContent() != null) {
            targetBoard.setContent(board.getContent());
        }

        if (board.getTitle() != null) {
            targetBoard.setTitle(board.getTitle());
        }

        if (board.getMainImage() != null) {
            targetBoard.setMainImage(board.getMainImage());
        }

        return targetBoard;
    }

}
