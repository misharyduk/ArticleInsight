package com.mike.articleinsight.review.comments.controller;

import com.mike.articleinsight.review.comments.dto.CommentRequestDto;
import com.mike.articleinsight.review.comments.dto.CommentResponseDto;
import com.mike.articleinsight.review.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/articles/{articleId}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByArticleId(@PathVariable("articleId") Long articleId){
        List<CommentResponseDto> commentsDto = commentService.getCommentsByArticleId(articleId);
        return ResponseEntity.ok(commentsDto);
    }

    @PostMapping
    public ResponseEntity<String> postComment(@RequestBody CommentRequestDto commentRequestDto){
        commentService.postComment(commentRequestDto);
        return ResponseEntity.ok("Comment has been successfully posted");
    }


}
