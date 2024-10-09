package com.mike.articleinsight.review.comments.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentResponseDto {

    private Long commentId;
    private String commentText;
    private String authorOfComment;
    private Date publishDate;

}
