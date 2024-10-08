package com.mike.articleinsight.review.comments.service.impl;

import com.mike.articleinsight.review.comments.dto.CommentRequestDto;
import com.mike.articleinsight.review.comments.dto.CommentResponseDto;
import com.mike.articleinsight.review.comments.entity.Comment;
import com.mike.articleinsight.review.comments.mapper.CommentMapper;
import com.mike.articleinsight.review.comments.repo.CommentRepository;
import com.mike.articleinsight.review.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponseDto> getCommentsByArticleId(Long articleId) {
        List<Comment> allComments = commentRepository.findAllByArticleId(articleId);
        return allComments.stream()
                .map(CommentMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public void postComment(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        CommentMapper.mapDtoToEntity(commentRequestDto, comment);
        comment.setPublishDate(new Date());
        commentRepository.save(comment);
    }
}
