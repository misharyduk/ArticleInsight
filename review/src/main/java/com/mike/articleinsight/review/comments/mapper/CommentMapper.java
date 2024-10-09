package com.mike.articleinsight.review.comments.mapper;

import com.mike.articleinsight.review.comments.dto.CommentRequestDto;
import com.mike.articleinsight.review.comments.dto.CommentResponseDto;
import com.mike.articleinsight.review.comments.entity.Comment;

public class CommentMapper {
    public static CommentResponseDto mapEntityToDto(Comment comment){
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .commentText(comment.getCommentText())
                .authorOfComment(comment.getAuthorOfComment())
                .publishDate(comment.getPublishDate())
                .build();
    }

    public static void mapDtoToEntity(CommentRequestDto requestDto, Comment comment){
        comment.setArticleId(requestDto.getArticleId());
        comment.setCommentText(requestDto.getCommentText());
        comment.setAuthorOfComment(requestDto.getAuthorOfComment());
    }
}
