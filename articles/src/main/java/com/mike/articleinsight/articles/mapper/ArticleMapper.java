package com.mike.articleinsight.articles.mapper;

import com.mike.articleinsight.articles.dto.ArticleRequestDto;
import com.mike.articleinsight.articles.dto.ArticleResponseDto;
import com.mike.articleinsight.articles.entity.Article;

public class ArticleMapper {
    public static ArticleResponseDto mapEntityToResponseDto(Article article){
        return ArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .text(article.getText())
                .keywords(article.getKeywords())
                .author(article.getAuthor())
                .publishingDate(article.getPublishingDate())
                .build();
    }

    public static void mapRequestDtoToEntity(ArticleRequestDto articleRequestDto, Article article){
        article.setText(articleRequestDto.getText());
        article.setAuthor(articleRequestDto.getAuthor());
        article.setPublishingDate(articleRequestDto.getPublishingDate());
    }
}
