package com.mike.articleinsight.review.likes.service;

import com.mike.articleinsight.review.likes.dto.LikeRequestDto;
import com.mike.articleinsight.review.likes.dto.LikeResponseDto;

import java.util.List;

public interface LikeService {
    List<LikeResponseDto> getLikesByArticleId(Long articleId);

    void addLike(LikeRequestDto likeRequestDto);
}
