package com.example.springdatajdbcexample.account;

import java.time.Instant;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

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
    private final UUID id;

    @NotBlank
    @Size(max = 50)
    private String loginId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private AccountState state;

    @Valid
    private EncryptString email;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();
}
