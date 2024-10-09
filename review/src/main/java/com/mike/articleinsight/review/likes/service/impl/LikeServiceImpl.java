package com.mike.articleinsight.review.likes.service.impl;

import com.mike.articleinsight.review.likes.dto.LikeRequestDto;
import com.mike.articleinsight.review.likes.dto.LikeResponseDto;
import com.mike.articleinsight.review.likes.entity.Like;
import com.mike.articleinsight.review.likes.mapper.LikeMapper;
import com.mike.articleinsight.review.likes.repo.LikeRepository;
import com.mike.articleinsight.review.likes.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public List<LikeResponseDto> getLikesByArticleId(Long articleId) {
        List<Like> allLikes = likeRepository.findAllByArticleId(articleId);
        return allLikes.stream()
                .map(LikeMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public void addLike(LikeRequestDto likeRequestDto) {
        Like like = new Like();
        LikeMapper.mapDtoToEntity(likeRequestDto, like);
        likeRepository.save(like);
    }

    @Override
    public Long countLikesByArticleId(Long articleId) {
        return likeRepository.countByArticleId(articleId);
    }
}
