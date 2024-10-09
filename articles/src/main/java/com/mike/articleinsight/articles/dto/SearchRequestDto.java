package com.mike.articleinsight.articles.dto;

import lombok.Data;

@Data
public class SearchRequestDto {
    private String field;
    private String value;
}
