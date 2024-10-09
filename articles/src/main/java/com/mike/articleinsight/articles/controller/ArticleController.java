package com.mike.articleinsight.articles.controller;

import com.mike.articleinsight.articles.dto.ArticleRequestDto;
import com.mike.articleinsight.articles.dto.ArticleResponseDto;
import com.mike.articleinsight.articles.dto.SearchRequestDto;
import com.mike.articleinsight.articles.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles(){
        List<ArticleResponseDto> articlesDto = articleService.getArticles();
        return ResponseEntity.ok(articlesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable("id") Long articleId){
        ArticleResponseDto articleDto = articleService.getArticleById(articleId);
        return ResponseEntity.ok(articleDto);
    }

    @PostMapping
    public ResponseEntity<Long> createArticle(@RequestBody ArticleRequestDto articleRequestDto){
        Long articleId = articleService.createArticle(articleRequestDto);
        return ResponseEntity.ok(articleId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long id){
        articleService.deleteArticleById(id);
        return ResponseEntity.ok("Article has been successfully deleted");
    }

    @GetMapping("/top/comments")
    public ResponseEntity<List<ArticleResponseDto>> getSortedArticlesByNumberOfComments(){
        List<ArticleResponseDto> articlesDto = articleService.getSortedArticlesByNumberOfCommentsDesc();
        return ResponseEntity.ok(articlesDto);
    }

    @GetMapping("/top/likes")
    public ResponseEntity<List<ArticleResponseDto>> getSortedArticlesByNumberOfLikes(){
        List<ArticleResponseDto> articlesDto = articleService.getSortedArticlesByNumberOfLikesDesc();
        return ResponseEntity.ok(articlesDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleResponseDto>> searchArticlesByField(@RequestBody SearchRequestDto searchRequestDto){
        List<ArticleResponseDto> articlesDto = articleService.searchArticlesByField(searchRequestDto);
        return ResponseEntity.ok(articlesDto);
    }

}
