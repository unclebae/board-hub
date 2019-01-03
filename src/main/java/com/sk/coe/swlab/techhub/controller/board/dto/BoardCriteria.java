package com.sk.coe.swlab.techhub.controller.board.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardCriteria {

    public static final int DEFAULT_COUNTS_PER_PAGE = 10;

    private String startDateTime;

    private String endDateTime;

    private Pageable pageable;
}
