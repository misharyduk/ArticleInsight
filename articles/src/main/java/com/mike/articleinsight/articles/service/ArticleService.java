package com.mike.articleinsight.articles.service;

import com.mike.articleinsight.articles.dto.ArticleRequestDto;
import com.mike.articleinsight.articles.dto.ArticleResponseDto;
import com.mike.articleinsight.articles.dto.SearchRequestDto;

import java.util.List;

public interface ArticleService {

    List<ArticleResponseDto> getArticles();

    ArticleResponseDto getArticleById(Long articleId);

    Long createArticle(ArticleRequestDto articleRequestDto);

    void deleteArticleById(Long id);

    List<ArticleResponseDto> getSortedArticlesByNumberOfCommentsDesc();

    List<ArticleResponseDto> getSortedArticlesByNumberOfLikesDesc();

    List<ArticleResponseDto> searchArticlesByField(SearchRequestDto searchRequestDto);
}
