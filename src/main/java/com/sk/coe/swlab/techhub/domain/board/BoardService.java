package com.sk.coe.swlab.techhub.domain.board;

import java.util.Optional;

import javax.persistence.EntityManager;
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

    public ResponseMessage<BoardDTO> findAllBoardByCategoryId(String category, Pageable pageable) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Board> criteria = criteriaBuilder.createQuery(Board.class);
        final Root<Board> from = criteria.from(Board.class);
        criteria.select(from);
//        criteria.where(criteriaBuilder.equal(Board_.category))

                return null;
//        final Page<BoardDTO> boardDTOS = new PageImpl<BoardDTO>(boardMapper.toDTO(all.getContent()), pageable, all.getTotalElements());
//        return ResponseMessage.<Page<BoardDTO>>builder()
//                .success(true)
//                .body(boardDTOS)
//                .build()
//                ;
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
