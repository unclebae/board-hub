package com.sk.coe.swlab.techhub.controller.comment;

import com.sk.coe.swlab.techhub.controller.board.dto.BoardDTO;
import com.sk.coe.swlab.techhub.controller.comment.dto.CommentDTO;
import com.sk.coe.swlab.techhub.domain.board.Board;
import com.sk.coe.swlab.techhub.domain.comment.Comment;
import com.sk.coe.swlab.techhub.domain.comment.CommentService;
import com.sk.coe.swlab.techhub.domain.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping()
    public ResponseEntity<ResponseMessage<CommentDTO>> writeBoard(@RequestBody Comment board) {

        return ResponseEntity.of(Optional.ofNullable(commentService.writeCommentRepository(board)));
    }
}
