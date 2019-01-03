package com.sk.coe.swlab.techhub.domain.comment;

import com.sk.coe.swlab.techhub.controller.comment.dto.CommentDTO;
import com.sk.coe.swlab.techhub.domain.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentRepository commentRepository;

    public ResponseMessage<CommentDTO> writeCommentRepository(Comment board) {
        final Comment savedComment = commentRepository.save(board);
        return ResponseMessage.<CommentDTO>builder()
                .success(true)
                .body(commentMapper.toDTO(savedComment))
                .build();
    }
}
