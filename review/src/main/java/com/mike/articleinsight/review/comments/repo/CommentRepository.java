package com.mike.articleinsight.review.comments.repo;

import com.mike.articleinsight.review.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArticleId(Long articleId);

    Long countByArticleId(Long articleId);
}
