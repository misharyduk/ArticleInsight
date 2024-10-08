package com.mike.articleinsight.review.likes.controller;

import com.mike.articleinsight.review.likes.dto.LikeRequestDto;
import com.mike.articleinsight.review.likes.dto.LikeResponseDto;
import com.mike.articleinsight.review.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/articles/{articleId}")
    public ResponseEntity<List<LikeResponseDto>> getAllLikesByArticleId(@PathVariable("articleId") Long articleId){
        List<LikeResponseDto> likes = likeService.getLikesByArticleId(articleId);
        return ResponseEntity.ok(likes);
    }

    @PostMapping
    public ResponseEntity<String> addLike(@RequestBody LikeRequestDto likeRequestDto){
        likeService.addLike(likeRequestDto);
        return ResponseEntity.ok("Like has been successfully added");
    }

}
