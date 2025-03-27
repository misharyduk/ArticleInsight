package com.mike.articleinsight.review.comments.controller;

import com.mike.articleinsight.review.comments.dto.CommentRequestDto;
import com.mike.articleinsight.review.comments.dto.CommentResponseDto;
import com.mike.articleinsight.review.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mike.articleinsight.review.comments.constants.CommentConstants.*;

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

    @GetMapping("/articles/{articleId}/count")
    public ResponseEntity<Long> countCommentsByArticleId(@PathVariable("articleId") Long articleId){
        Long numberOfComments = commentService.countCommentsByArticleId(articleId);
        return ResponseEntity.ok(numberOfComments);
    }

    @PostMapping
    public ResponseEntity<String> postComment(@RequestBody CommentRequestDto commentRequestDto){
        commentService.postComment(commentRequestDto);
        return ResponseEntity.ok(COMMENT_201_MESSAGE);
    }


}
