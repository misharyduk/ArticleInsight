package com.mike.articleinsight.review.likes.repo;

import com.mike.articleinsight.review.likes.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByArticleId(Long articleId);

}
