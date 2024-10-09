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
                .category(article.getCategory())
                .author(article.getAuthor())
                .publishingDate(article.getPublishingDate())
                .build();
    }

    public static void mapRequestDtoToEntity(ArticleRequestDto articleRequestDto, Article article){
        article.setTitle(articleRequestDto.getTitle());
        article.setText(articleRequestDto.getText());
        article.setCategory(articleRequestDto.getCategory());
        article.setAuthor(articleRequestDto.getAuthor());
    }
}
