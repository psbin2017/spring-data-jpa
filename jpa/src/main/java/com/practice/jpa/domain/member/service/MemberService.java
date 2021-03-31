package com.practice.jpa.domain.member.service;

import com.practice.jpa.domain.member.domain.Member;
import com.practice.jpa.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long register(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    public List<Member> findByAge(Integer age) {
        return memberRepository.findByAge(age);
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name)
                    .orElseThrow(() -> new IllegalArgumentException(name+ "의 회원은 없습니다."));
    }

}
