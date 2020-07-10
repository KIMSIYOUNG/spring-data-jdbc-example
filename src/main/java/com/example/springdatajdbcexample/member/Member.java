package com.example.springdatajdbcexample.member;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.With;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(of = "id")
@Builder
@Getter
public class Member {
    @Id @With
    private final Long id;
    private final String name;
    private String nickname;

    void changeInfo(String nickname) {
        this.nickname = nickname;
    }
}
