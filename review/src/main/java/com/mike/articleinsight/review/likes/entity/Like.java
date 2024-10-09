package com.mike.articleinsight.review.likes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "article_like")
@Getter @Setter
public class Like {

    @SequenceGenerator(name = "like_id_sequence_generator", sequenceName = "like_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_id_sequence_generator")
    @Id
    @Column(name = "like_id")
    private Long id;
    @Column(name = "article_id")
    private Long articleId;
    @Column(name = "like_author")
    private String authorOfLike;

}
