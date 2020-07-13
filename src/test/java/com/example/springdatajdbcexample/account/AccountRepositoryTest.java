package com.example.springdatajdbcexample.account;

import static com.example.springdatajdbcexample.account.Account.*;
import static com.example.springdatajdbcexample.account.AccountTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.test.annotation.DirtiesContext;

import com.example.springdatajdbcexample.support.EncryptString;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AccountRepositoryTest {
    private static final String TEST_EMAIL = "TEST@EMAIL.COM";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JdbcAggregateOperations jdbcAggregateOperations;

    @DisplayName("삭제 테스트를 위한 Dummy데이터를 추가하는 메서드입니다.")
    @BeforeEach
    void addDummyAccounts() {
        for (int i =1; i<10; i++) {
            Account account = builder()
                .loginId(String.valueOf(i))
                .name(TEST_NAME)
                .state(AccountState.ACTIVE)
                .email(new EncryptString(TEST_EMAIL))
                .build();
            accountRepository.save(account);
        }
    }

    @DisplayName("이메일이 정상적으로 encrypt, decrypt 되는지 확인합니다.")
    @Test
    void encrypt() {
        Account account = accountRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("Dummy 데이터가 정상 Insert 되지 않았습니다."));

        assertThat(account.getEmail().getValue()).isEqualTo(TEST_EMAIL);
    }

    @DisplayName("아이디를 기반으로 계정을 삭제합니다. 계정은 삭제되지 않고 상태만 변경됩니다.")
    @Test
    void deleteById() {
        accountRepository.deleteById(1L);
        Account account = accountRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("Dummy 데이터가 정상 Insert 되지 않았습니다."));

        assertThat(account).isNotNull();
        assertThat(account.getState()).isEqualTo(AccountState.DELETED);
    }

    @DisplayName("모두 삭제하는 경우에도 DB에 남아 있으며 각 객체의 상태만 변경됩니다.")
    @Test
    void deleteAll() {
        Iterable<Account> accounts = accountRepository.findAll();
        accountRepository.deleteAll(accounts);

        assertThat(new ArrayList<Account>((Collection<? extends Account>)accounts).size()).isEqualTo(9);
    }

    @Test
    void deleteByEntity() {
        Account account = accountRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("Dummy 데이터가 정상 Insert 되지 않았습니다."));

        accountRepository.delete(account);
        assertThat(account.getState()).isEqualTo(AccountState.DELETED);
    }
}