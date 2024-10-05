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
    @Column(name = "article_title", insertable = false)
    private String title;
    @Column(name = "article_text", columnDefinition = "TEXT", updatable = false)
    private String text;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "article_keyword", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "article_keyword")
    private List<String> keywords;
    @Column(name = "article_author", updatable = false)
    private String author;
    @Column(name = "article_publishing_date", updatable = false)
    private Date publishingDate;
}
