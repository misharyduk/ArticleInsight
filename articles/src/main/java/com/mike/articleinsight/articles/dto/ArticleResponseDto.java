package com.mike.articleinsight.articles.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String text;
    private List<String> keywords;
    private String author;
    private Date publishingDate;

}
