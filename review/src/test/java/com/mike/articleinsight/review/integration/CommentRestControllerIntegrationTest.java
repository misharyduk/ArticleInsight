package com.mike.articleinsight.review.integration;

import com.mike.articleinsight.review.ReviewApplication;
import com.mike.articleinsight.review.comments.entity.Comment;
import com.mike.articleinsight.review.comments.repo.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ReviewApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CommentRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void givenComments_whenGetComments_thenStatus200() throws Exception{
        Long actualCommentId = createTextComment(1L, "Great Job!", "Mykhailo Rudyk");

        mvc.perform(get("/api/v1/review/comments/articles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].commentId", is(1)));
    }

    private Long createTextComment(Long articleId, String text, String author) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCommentText(text);
        comment.setAuthorOfComment(author);
        comment.setPublishDate(new Date());

        commentRepository.save(comment);

        return comment.getCommentId();
    }

}
