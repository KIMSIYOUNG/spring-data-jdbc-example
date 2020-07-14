package com.example.springdatajdbcexample.repo;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.core.Ordered;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;

import com.example.springdatajdbcexample.account.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
@Builder
public class Repo {
    @Id
    private final String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    private final AggregateReference<Account, @NotNull Long> createdBy;

    @NotNull
    @PastOrPresent
    private final Instant createdAt = Instant.now();

    public Repo copyWithId(Repo other, String id) {
        return Repo.builder()
            .id(id)
            .name(other.name)
            .description(other.description)
            .createdBy(other.createdBy)
            .build();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public static class RepoBeforeSaveCallback implements BeforeSaveCallback<Repo>, Ordered {
        private static final DateTimeFormatter ID_PREFIX_FORMAT = DateTimeFormatter
            .ofPattern("yyyyMMddHHmmss")
            .withZone(ZoneId.of("Asia/Seoul"));

        private static String generateId(Repo repo) {
            return new StringJoiner("-")
                .add(ID_PREFIX_FORMAT.format(repo.getCreatedAt()))
                .add(repo.getName())
                .toString();
        }

        @Override
        public Repo onBeforeSave(Repo aggregate, MutableAggregateChange<Repo> aggregateChange) {
            if(aggregate.id == null) {
                return aggregate.copyWithId(aggregate, generateId(aggregate));
            }
            return aggregate;
        }

        @Override
        public int getOrder() {
            return Ordered.LOWEST_PRECEDENCE;
        }
    }

}
