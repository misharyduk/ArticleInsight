package com.mike.articleinsight.articles.repo;

import com.mike.articleinsight.articles.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Search methods
    List<Article> findByTitleContainingIgnoreCase(String fieldValue);

    List<Article> findByAuthorContainingIgnoreCase(String fieldValue);
}
