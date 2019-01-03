package com.sk.coe.swlab.techhub.controller.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long id;

    private String category;

    private String boardType;

    private String title;

    private String content;

    private String mainImage;

    private Long viewCount;

    private Long likeCount;

    private Long dislikeCount;

    private String createdAt;

    private String createdBy;

    private String modifiedAt;

    private String modifiedBy;
}
