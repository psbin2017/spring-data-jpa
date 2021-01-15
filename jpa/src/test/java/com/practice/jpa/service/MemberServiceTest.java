package com.practice.jpa.service;

import com.practice.jpa.domain.member.domain.Member;
import com.practice.jpa.domain.member.repository.MemberRepository;
import com.practice.jpa.domain.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    // junit4 @After
    @AfterEach
    public void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("아무개를 등록하고 고유키 값을 확인한다.")
    @Test
    public void register() {
        // given & when
        Member member = Member.builder()
                                .name("아무개")
                                .age(20)
                                .build();

        Long id = memberService.register(member);

        // then
        Assertions.assertEquals(id, 1L);
    }

    @DisplayName("홍길동을 등록하고 이름으로 홍길동을 찾아 나이를 비교한다")
    @Test
    public void findByName() {
        // given
        memberService.register(Member.builder()
                                                .name("홍길동")
                                                .age(30)
                                                .build());

        // when
        Member findMember = memberService.findByName("홍길동");

        // then
        Assertions.assertEquals(findMember.getAge(), 30);
    }
}
