package com.example.springdatajdbcexample.repo;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;

import com.example.springdatajdbcexample.repo.Repo.RepoBeforeSaveCallback;

class RepoBeforeSaveCallbackTest {
    RepoBeforeSaveCallback beforeSaveCallback = new RepoBeforeSaveCallback();

    @DisplayName("저장하기전에, 아이디가 없는 객체는 커스텀한 아이디가 적용됩니다.")
    @Test
    void beforeSaveWithoutId() {
        Repo repoWithoutId = Repo.builder()
            .name("TEST")
            .description("TEST")
            .createdBy(AggregateReference.to(1L))
            .build();

        Repo findRepo = beforeSaveCallback.onBeforeSave(repoWithoutId, MutableAggregateChange.forSave(repoWithoutId));

        assertAll(
            () -> assertThat(repoWithoutId == findRepo).isFalse(),
            () -> assertThat(repoWithoutId.getId()).isNull(),
            () -> assertThat(findRepo.getId()).isNotNull(),
            () -> assertThat(repoWithoutId.getName()).isEqualTo(findRepo.getName()),
            () -> assertThat(repoWithoutId.getDescription()).isEqualTo(findRepo.getDescription()),
            () -> assertThat(repoWithoutId.getCreatedBy()).isEqualTo(findRepo.getCreatedBy())
        );
    }

    @DisplayName("저장하기전에, 아이디가 있는 경우 원래의 객체를 리턴합니다.")
    @Test
    void beforeSaveWithId() {
        Repo repoWithoutId = Repo.builder()
            .id("EXIST")
            .name("TEST")
            .description("TEST")
            .createdBy(AggregateReference.to(1L))
            .build();
        Repo findRepo = beforeSaveCallback.onBeforeSave(repoWithoutId, MutableAggregateChange.forSave(repoWithoutId));

        assertThat(findRepo == repoWithoutId);
        assertThat(repoWithoutId.getId()).isEqualTo(findRepo.getId());
    }
}