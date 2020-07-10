package com.example.springdatajdbcexample.account;

import java.time.Instant;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import com.example.springdatajdbcexample.support.EncryptString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Account {
    @Id @With
    private final Long id;

    @NotBlank
    @Size(max = 50)
    private String loginId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private AccountState state;

    private String email;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();

    void delete() {
        this.state = AccountState.DELETED;
    }
}
