package com.mike.articleinsight.review.likes.mapper;

import com.mike.articleinsight.review.likes.dto.LikeRequestDto;
import com.mike.articleinsight.review.likes.dto.LikeResponseDto;
import com.mike.articleinsight.review.likes.entity.Like;

public class LikeMapper {
    public static LikeResponseDto mapEntityToDto(Like like){
        return LikeResponseDto.builder()
                .likeId(like.getId())
                .authorOfLike(like.getAuthorOfLike())
                .build();
    }

    public static void mapDtoToEntity(LikeRequestDto requestDto, Like like){
        like.setArticleId(requestDto.getArticleId());
        like.setAuthorOfLike(requestDto.getAuthorOfLike());
    }
}
