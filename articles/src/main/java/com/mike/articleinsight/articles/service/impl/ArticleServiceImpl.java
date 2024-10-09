package com.mike.articleinsight.articles.service.impl;

import com.mike.articleinsight.articles.dto.ArticleRequestDto;
import com.mike.articleinsight.articles.dto.ArticleResponseDto;
import com.mike.articleinsight.articles.dto.SearchRequestDto;
import com.mike.articleinsight.articles.entity.Article;
import com.mike.articleinsight.articles.exception.ResourceNotFoundException;
import com.mike.articleinsight.articles.mapper.ArticleMapper;
import com.mike.articleinsight.articles.repo.ArticleRepository;
import com.mike.articleinsight.articles.service.ArticleService;
import com.mike.articleinsight.articles.service.feign_client.review.ReviewFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<ArticleResponseDto> getSortedArticlesByNumberOfCommentsDesc() {
        return getArticles().stream()
                .sorted((a1, a2) -> a2.getNumberOfComments().compareTo(a1.getNumberOfComments()))
                .toList();
    }

    @Override
    public List<ArticleResponseDto> getSortedArticlesByNumberOfLikesDesc() {
        return getArticles().stream()
                .sorted((a1, a2) -> a2.getNumberOfLikes().compareTo(a1.getNumberOfLikes()))
                .toList();
    }

    @Override
    public List<ArticleResponseDto> searchArticlesByField(SearchRequestDto searchRequestDto) {
        String sortField = searchRequestDto.getField();
        String fieldValue = searchRequestDto.getValue();

        List<Article> articles = new ArrayList<>();
        if(sortField.equalsIgnoreCase("title")){
            articles = articleRepository.findByTitleContainingIgnoreCase(fieldValue);
        } else if(sortField.equalsIgnoreCase("author")){
            articles = articleRepository.findByAuthorContainingIgnoreCase(fieldValue);
        }

        return articles.stream()
                .map(ArticleMapper::mapEntityToResponseDto)
                .peek(a -> a.setNumberOfLikes(reviewFeignClient.countLikesByArticleId(a.getId()).getBody()))
                .peek(a -> a.setNumberOfComments(reviewFeignClient.countCommentsByArticleId(a.getId()).getBody()))
                .toList();
    }
}
