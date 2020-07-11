package com.example.springdatajdbcexample.comment;

import java.time.Instant;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;

import com.example.springdatajdbcexample.account.Account;
import com.example.springdatajdbcexample.issue.Issue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "id")
@ToString
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Comment {
    @Id
    private final Long id;

    @PositiveOrZero
    @Version
    private long version;

    private final AggregateReference<Issue, @NotNull Long> issueId;

    @Valid
    @Column("ID")
    private final CommentContent content;

    private final AggregateReference<Account, @NotNull Long> createdBy;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private final Instant createdAt = Instant.now();
}
