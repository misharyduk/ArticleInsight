package com.mike.articleinsight.articles.service.feign_client.review;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("review")
public interface ReviewFeignClient {

    // Comments API
    @GetMapping("/articles/{articleId}/count")
    ResponseEntity<Long> countCommentsByArticleId(@PathVariable("articleId") Long articleId);

    // Likes API
    @GetMapping("/articles/{articleId}/count")
    ResponseEntity<Long> countLikesByArticleId(@PathVariable("articleId") Long articleId);

}
