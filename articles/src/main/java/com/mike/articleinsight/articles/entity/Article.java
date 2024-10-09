package com.mike.articleinsight.articles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Article {
    @SequenceGenerator(name = "article_sequence_generator", sequenceName = "article_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_sequence_generator")
    @Id
    private Long id;
    @Column(name = "article_title")
    private String title;
    @Column(name = "article_text", columnDefinition = "TEXT")
    private String text;
    @Column(name = "article_category")
    private String category;
    @Column(name = "article_author")
    private String author;
    @Column(name = "article_publishing_date")
    private Date publishingDate;
}
