package com.mike.articleinsight.articles.service.impl;

import com.mike.articleinsight.articles.dto.ArticleRequestDto;
import com.mike.articleinsight.articles.dto.ArticleResponseDto;
import com.mike.articleinsight.articles.entity.Article;
import com.mike.articleinsight.articles.exception.ResourceNotFoundException;
import com.mike.articleinsight.articles.mapper.ArticleMapper;
import com.mike.articleinsight.articles.repo.ArticleRepository;
import com.mike.articleinsight.articles.service.ArticleService;
import com.mike.articleinsight.articles.service.feign_client.review.ReviewFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ReviewFeignClient reviewFeignClient;

    @Override
    public List<ArticleResponseDto> getArticles() {
        List<Article> allArticles = articleRepository.findAll();
        return allArticles.stream()
                .map(ArticleMapper::mapEntityToResponseDto)
                .peek(a -> a.setNumberOfLikes(reviewFeignClient.countLikesByArticleId(a.getId()).getBody()))
                .peek(a -> a.setNumberOfComments(reviewFeignClient.countCommentsByArticleId(a.getId()).getBody()))
                .toList();
    }

    @Override
    public ArticleResponseDto getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", String.valueOf(id)));
        ArticleResponseDto responseDto = ArticleMapper.mapEntityToResponseDto(article);
        responseDto.setNumberOfLikes(reviewFeignClient.countLikesByArticleId(article.getId()).getBody());
        responseDto.setNumberOfComments(reviewFeignClient.countCommentsByArticleId(article.getId()).getBody());
        return responseDto;
    }

    @Override
    public Long createArticle(ArticleRequestDto articleRequestDto) {
        Article article = new Article();
        ArticleMapper.mapRequestDtoToEntity(articleRequestDto, article);
        article.setPublishingDate(new Date());
        articleRepository.save(article);
        return article.getId();
    }

    @Override
    public void deleteArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", String.valueOf(id)));
        articleRepository.delete(article);
    }

    @Override
    public List<ArticleResponseDto> getSortedArticlesByNumberOfCommentsAsc() {
        return getArticles().stream()
                .sorted((a1, a2) -> a1.getNumberOfComments().compareTo(a2.getNumberOfComments()))
                .toList();
    }

    @Override
    public List<ArticleResponseDto> getSortedArticlesByNumberOfLikesAsc() {
        return getArticles().stream()
                .sorted((a1, a2) -> a1.getNumberOfLikes().compareTo(a2.getNumberOfLikes()))
                .toList();
    }
}
