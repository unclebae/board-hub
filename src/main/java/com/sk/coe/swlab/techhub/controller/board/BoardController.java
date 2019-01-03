package com.sk.coe.swlab.techhub.controller.board;

import com.sk.coe.swlab.techhub.controller.board.dto.BoardCriteria;
import com.sk.coe.swlab.techhub.controller.board.dto.BoardDTO;
import com.sk.coe.swlab.techhub.domain.board.Board;
import com.sk.coe.swlab.techhub.domain.board.BoardService;
import com.sk.coe.swlab.techhub.domain.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseMessage<BoardDTO>> writeBoard(@RequestBody Board board) {

        return ResponseEntity.of(Optional.ofNullable(boardService.writeBoard(board)));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseMessage> deleteBoard(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.of(Optional.ofNullable(boardService.deleteBoardById(boardId)));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage<BoardDTO>> updateBoard(@RequestBody Board board) {
        return ResponseEntity.of(Optional.ofNullable(boardService.updateBoard(board)));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseMessage<Page<BoardDTO>>> getAllBoards(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 2)Pageable pageable) {
        return ResponseEntity.of(Optional.ofNullable(boardService.findAllBoard(pageable)));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseMessage<BoardDTO>> findBoardById(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.of(Optional.ofNullable(boardService.findBoardById(boardId)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseMessage<BoardDTO>> findBoardById(@PathVariable("category") String category, Pageable pageable) {
        return ResponseEntity.of(Optional.ofNullable(boardService.findAllBoardByCategoryId(category, pageable)));
    }
}
