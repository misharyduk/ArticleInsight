package com.mike.articleinsight.review.comments.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class Comment {

    @SequenceGenerator(name = "comment_id_sequence_generator", sequenceName = "comment_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_sequence_generator")
    @Id
    @Column(name = "id")
    private Long commentId;
    @Column(name = "article_id")
    private Long articleId;
    @Column(name = "comment_text")
    private String commentText;
    @Column(name = "comment_author")
    private String authorOfComment;
    @Column(name = "comment_publish_date")
    private Date publishDate;


}
