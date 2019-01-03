package com.sk.coe.swlab.techhub.controller.comment.dto;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentDTO {

    private Long id;

    private Long parentCommentId;

    private Long boardId;

    private String comment;

    private int level;

    private Long likeCount;

    private Long dislikeCount;

    private String createdAt;

    private String createdBy;

    private String modifiedAt;

    private String modifiedBy;


}
