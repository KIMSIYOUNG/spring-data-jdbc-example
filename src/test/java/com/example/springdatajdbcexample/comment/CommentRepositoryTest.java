package com.example.springdatajdbcexample.comment;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CommentRepositoryTest {
    public static final String TEST_BODY = "TEST_BODY";
    public static final String TEST_MIME = "TEST_MIME";

    @Autowired
    CommentRepository commentRepository;

    private final List<Comment> comments = Arrays.asList(
        Comment.builder()
            .issueId(AggregateReference.to(Math.abs(new Random().nextLong())))
            .content(new CommentContent(TEST_BODY, TEST_MIME))
            .createdBy(AggregateReference.to(Math.abs(new Random().nextLong())))
            .build(),
        Comment.builder()
            .issueId(AggregateReference.to(Math.abs(new Random().nextLong())))
            .content(new CommentContent(TEST_BODY, TEST_MIME))
            .createdBy(AggregateReference.to(Math.abs(new Random().nextLong())))
            .build()
    );

    @DisplayName("정상적으로 Comment 가 생성되고, 데이터베이스에서 조회된다.")
    @Test
    void saveTest() {
        Comment comment = comments.get(0);
        Comment savedComment = commentRepository.save(comment);

        assertThat(comment.getContent()).isEqualTo(savedComment.getContent());
    }
}