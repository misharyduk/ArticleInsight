package com.mike.articleinsight.review.comments.service;

import com.mike.articleinsight.review.comments.dto.CommentRequestDto;
import com.mike.articleinsight.review.comments.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getCommentsByArticleId(Long articleId);

    void postComment(CommentRequestDto commentRequestDto);
}
