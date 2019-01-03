package com.sk.coe.swlab.techhub.domain.comment;

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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private Long parentCommentId;

    private Long boardId;

    private String comment;

    @Column(name = "level", insertable = false)
    private Long level;

    @Column(name = "likeCount", insertable = false)
    private Long likeCount;

    @Column(name = "dislikeCount", insertable = false)
    private Long dislikeCount;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;


}
