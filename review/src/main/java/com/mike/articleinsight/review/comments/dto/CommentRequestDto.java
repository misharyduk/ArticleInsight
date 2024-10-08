package com.mike.articleinsight.review.comments.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentRequestDto {

    private Long articleId;
    private String commentText;
    private String authorOfComment;

}
