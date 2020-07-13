package com.example.springdatajdbcexample.issue;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.example.springdatajdbcexample.label.Label;
import repo.Repo;

public interface IssueRepositoryCustom {
    Page<Issue> findByRepoIdAndAttachedLabelsLabelId(
        AggregateReference<Repo, String> repoId,
        AggregateReference<Label, UUID> labelId,
        Pageable pageable);
}
