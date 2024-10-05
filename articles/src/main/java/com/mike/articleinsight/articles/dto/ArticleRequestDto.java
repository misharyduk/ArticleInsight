package com.mike.articleinsight.articles.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleRequestDto {

    private String text;
    private String author;
    private Date publishingDate;

}
