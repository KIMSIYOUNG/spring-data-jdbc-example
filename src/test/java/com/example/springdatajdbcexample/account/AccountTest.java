package com.example.springdatajdbcexample.account;

import static org.assertj.core.api.Assertions.*;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    public static final String TEST_NAME = "KYLE";

    @DisplayName("빌더로 객체를 생성할 때 현재의 시간이 같이 채워집니다.")
    @Test
    void builderDefaultTest() {
        Account account = Account.builder()
            .name(TEST_NAME)
            .build();

        assertThat(account.getCreatedAt()).isNotNull();
        assertThat(account.getCreatedAt().isBefore(Instant.now()));
    }

    @DisplayName("생성된 객체의 상태를 변경하는 메서드가 정상 작동하며 객체는 삭제되지 않습니다.")
    @Test
    void changeState() {
        Account account = Account.builder()
            .state(AccountState.ACTIVE)
            .build();
        account.delete();

        assertThat(account.getState()).isEqualTo(AccountState.DELETED);
        assertThat(account).isNotNull();
    }
}