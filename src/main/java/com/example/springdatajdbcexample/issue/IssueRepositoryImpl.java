package com.example.springdatajdbcexample.issue;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.convert.EntityRowMapper;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.springdatajdbcexample.label.Label;
import com.example.springdatajdbcexample.repo.Repo;

public class IssueRepositoryImpl implements IssueRepositoryCustom {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final EntityRowMapper<Issue> rowMapper;

    public IssueRepositoryImpl(
        NamedParameterJdbcOperations namedParameterJdbcOperations,
        RelationalMappingContext mappingContext,
        JdbcConverter jdbcConverter) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.rowMapper = new EntityRowMapper<>(
            (RelationalPersistentEntity<Issue>)mappingContext.getRequiredPersistentEntity(Issue.class),
            jdbcConverter
        );
    }

    @Override
    public Page<Issue> findByRepoIdAndAttachedLabelsLabelId(
        AggregateReference<Repo, String> repoId,
        AggregateReference<Label, UUID> labelId, Pageable pageable) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("repoId", repoId.getId())
            .addValue("labelId", labelId.getId())
            .addValue("offset", pageable.getOffset())
            .addValue("pageSize", pageable.getPageSize());

        List<Issue> issues = this.namedParameterJdbcOperations.query(
            IssueSql.selectByRepoIdAndAttachedLabelsLabelId(pageable.getSort()), parameterSource, this.rowMapper);

        return PageableExecutionUtils.getPage(issues, pageable, () ->
            this.namedParameterJdbcOperations.queryForObject(IssueSql.countByRepoIdAndAttachedLabelsLabelId(), parameterSource, Long.class));
    }
}
