package com.mike.articleinsight.articles.repo;

import com.mike.articleinsight.articles.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
