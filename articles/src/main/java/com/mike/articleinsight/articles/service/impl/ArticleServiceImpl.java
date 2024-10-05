package com.mike.articleinsight.articles.service.impl;

import com.mike.articleinsight.articles.dto.ArticleRequestDto;
import com.mike.articleinsight.articles.dto.ArticleResponseDto;
import com.mike.articleinsight.articles.entity.Article;
import com.mike.articleinsight.articles.exception.ResourceNotFoundException;
import com.mike.articleinsight.articles.mapper.ArticleMapper;
import com.mike.articleinsight.articles.repo.ArticleRepository;
import com.mike.articleinsight.articles.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleResponseDto> getArticles() {
        List<Article> allArticles = articleRepository.findAll();
        return allArticles.stream()
                .map(ArticleMapper::mapEntityToResponseDto)
                .toList();
    }

    @Override
    public ArticleResponseDto getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", String.valueOf(id)));
        return ArticleMapper.mapEntityToResponseDto(article);
    }

    @Override
    public Long createArticle(ArticleRequestDto articleRequestDto) {
        Article article = new Article();
        ArticleMapper.mapRequestDtoToEntity(articleRequestDto, article);
        articleRepository.save(article);
        return article.getId();
    }

    @Override
    public void deleteArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", String.valueOf(id)));
        articleRepository.delete(article);
    }
}
