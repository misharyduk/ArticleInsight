package com.mike.articleinsight.review.integration;

import com.mike.articleinsight.review.ReviewApplication;
import com.mike.articleinsight.review.comments.entity.Comment;
import com.mike.articleinsight.review.comments.repo.CommentRepository;
import com.mike.articleinsight.review.likes.entity.Like;
import com.mike.articleinsight.review.likes.repo.LikeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ReviewApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class LikeRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LikeRepository likeRepository;

    @Test
    public void givenLikes_whenGetLikes_thenStatus200() throws Exception{
        Long actualLikeId = postLike(1L, "Mykhailo Rudyk");

        mvc.perform(get("/api/v1/review/likes/articles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].likeId", is(1)));
    }

    private Long postLike(Long articleId, String author) {
        Like like = new Like();
        like.setArticleId(articleId);
        like.setAuthorOfLike(author);

        likeRepository.save(like);

        return like.getId();
    }

}
