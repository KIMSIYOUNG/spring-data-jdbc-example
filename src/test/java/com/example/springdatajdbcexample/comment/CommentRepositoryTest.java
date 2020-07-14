package com.example.springdatajdbcexample.comment;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
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
    private static final String TEST_BODY = "TEST_BODY";
    private static final String TEST_MIME = "TEST_MIME";
    private static final Long CREATOR1_ID = Math.abs(new Random().nextLong());
    private static final Long CREATOR2_ID = Math.abs(new Random().nextLong());

    @Autowired
    CommentRepository commentRepository;

    private final List<Comment> comments = Arrays.asList(
        Comment.builder()
            .issueId(AggregateReference.to(Math.abs(new Random().nextLong())))
            .content(new CommentContent(TEST_BODY, TEST_MIME))
            .createdBy(AggregateReference.to(CREATOR1_ID))
            .build(),
        Comment.builder()
            .issueId(AggregateReference.to(Math.abs(new Random().nextLong())))
            .content(new CommentContent(TEST_BODY, TEST_MIME))
            .createdBy(AggregateReference.to(CREATOR2_ID))
            .build()
    );

    @DisplayName("정상적으로 Comment 가 생성되고, 데이터베이스에서 조회된다.")
    @Test
    void saveTest() {
        Comment comment = comments.get(0);
        Comment savedComment = commentRepository.save(comment);

        assertAll(
            () -> assertThat(comment.getContent()).isEqualTo(savedComment.getContent()),
            () -> assertThat(savedComment.getId()).isNotNull(),
            () -> assertThat(savedComment.getVersion()).isEqualTo(1L)
        );

        Comment findComment = commentRepository.findById(savedComment.getId())
            .orElseThrow(() -> new IllegalArgumentException("데이터가 정상 등록되지 않았습니다."));

        assertAll(
            () -> assertThat(findComment.getId()).isEqualTo(savedComment.getId()),
            () -> assertThat(findComment.getContent().getBody()).isEqualTo(TEST_BODY),
            () -> assertThat(findComment.getContent().getMimeType()).isEqualTo(TEST_MIME),
            () -> assertThat(findComment.getCreatedBy().getId()).isEqualTo(CREATOR1_ID),
            () -> assertThat(findComment.getCreatedAt()).isBefore(Instant.now())
        );
    }
}