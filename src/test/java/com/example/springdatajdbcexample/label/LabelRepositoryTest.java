package com.example.springdatajdbcexample.label;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.test.annotation.DirtiesContext;

import com.example.springdatajdbcexample.repo.RepoRepository;

@DirtiesContext
@SpringBootTest
class LabelRepositoryTest {
    private static final String TEST_REPO_ID = "TEST_REPO_ID";
    private static final String TEST_NAME = "TEST_NAME";
    private static final String TEST_COLOR = "TEST_COLOR";
    private static final String NOT_EXIST_REPO_ID = "NOT_EXIST_RANDOM_ID";

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    RepoRepository repoRepository;

    @DisplayName("존재하지 않는 의존 객체 아이디를 설정할 수 있다.")
    @Test
    void createLabelWithoutDependency() {
        Label label = new Label(AggregateReference.to(NOT_EXIST_REPO_ID), TEST_NAME, TEST_COLOR);

        assertThat(labelRepository.save(label)).isNotNull();
    }

    @DisplayName("라벨이 정상적으로 생성되고, 찾은 값이 모두 일치합니다.")
    @Test
    void create() {
        Label label = new Label(AggregateReference.to(TEST_REPO_ID), TEST_NAME, TEST_COLOR);
        Label savedLabel = labelRepository.save(label);

        assertAll(
            () -> assertThat(savedLabel.getRepoId().getId()).isEqualTo(TEST_REPO_ID),
            () -> assertThat(savedLabel.getId()).isNotNull(),
            () -> assertThat(savedLabel.isNew()).isFalse()
        );

        Optional<Label> findLabel = labelRepository.findById(savedLabel.getId());

        assertAll(
            () -> assertThat(findLabel.isPresent()).isTrue(),
            () -> assertThat(findLabel.get().isNew()).isFalse(),
            () -> assertThat(findLabel.get().getColor()).isEqualTo(TEST_COLOR),
            () -> assertThat(findLabel.get().getName()).isEqualTo(TEST_NAME),
            () -> assertThat(findLabel.get().getRepoId().getId()).isEqualTo(TEST_REPO_ID)
        );
    }
}