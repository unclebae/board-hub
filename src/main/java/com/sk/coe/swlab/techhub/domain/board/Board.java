package com.sk.coe.swlab.techhub.domain.board;

import com.sk.coe.swlab.techhub.domain.consts.BoardType;
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
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String category;

    @Column(name = "boardType")
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    private String title;

    private String content;

    @Column(name = "mainImage")
    private String mainImage;

    @Column(name = "viewCount", insertable = false)
    private Long viewCount;

    @Column(name = "likeCount", insertable = false)
    private Long likeCount;

    @Column(name = "dislikeCount", insertable = false)
    private Long dislikeCount;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "modifiedBy")
    private String modifiedBy;

}
