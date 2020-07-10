package com.example.springdatajdbcexample.member;

import static com.example.springdatajdbcexample.member.Member.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

@DataJdbcTest
class MemberRepositoryTest {
    private static final String TEST_NAME = "KYLE";

    Member member = builder().name(TEST_NAME).build();

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("저장하기전 객체와 저장한 객체는 같지 않다.")
    @Test
    void save() {
        Member saveMember = memberRepository.save(member);

        assertThat(member.getId()).isNull();
        assertThat(saveMember.getId()).isNotNull();
    }

    @DisplayName("저장된 객체를 수정하여 저장한 후 받은 객체는 수정한 객체다.")
    @Test
    void update() {
        Member savedMember = memberRepository.save(member);
        savedMember.changeInfo("SIYOUNG");
        Member updatedMember = memberRepository.save(savedMember);

        assertThat(savedMember.equals(updatedMember)).isTrue();
    }
}