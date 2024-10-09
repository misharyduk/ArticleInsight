package com.mike.articleinsight.articles.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleRequestDto {

    private String title;
    private String text;
    private String category;
    private String author;

}
