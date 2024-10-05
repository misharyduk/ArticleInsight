package com.mike.articleinsight.articles.controller;

import com.mike.articleinsight.articles.dto.ArticleRequestDto;
import com.mike.articleinsight.articles.dto.ArticleResponseDto;
import com.mike.articleinsight.articles.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
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

}