package com.example.springdatajdbcexample.issue;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import com.example.springdatajdbcexample.account.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import repo.Repo;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Issue {
    @Id
    private final Long id;

    @PositiveOrZero
    @Version
    private long version;

    private final AggregateReference<Repo, @NotBlank @Size(max=200) String> repoId;

    @NotNull
    @PositiveOrZero
    private Long issueNo;

    @NotNull
    private Status status;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Valid
    @Column("ISSUE_ID") // default: "ISSUE"
    private IssueContent content;

    @Valid
    @MappedCollection(idColumn = "ISSUE_ID", keyColumn = "ATTACHED_AT")
    @Builder.Default
    private List<IssueAttachedLabel> attachedLabels = new ArrayList<>();

    private AggregateReference<Account, @NotNull Long> createdBy;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();

}
