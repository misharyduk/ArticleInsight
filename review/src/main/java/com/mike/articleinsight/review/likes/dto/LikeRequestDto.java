package com.mike.articleinsight.review.likes.dto;

import lombok.Data;

@Data
public class LikeRequestDto {
    private Long articleId;
    private String authorOfLike;
}
