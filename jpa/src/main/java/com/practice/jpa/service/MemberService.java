package com.practice.jpa.service;

import com.practice.jpa.domain.Member;
import com.practice.jpa.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long register(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Transactional(readOnly = true)
    public Member findByName(String name) {
        return memberRepository.findByName(name)
                    .orElseThrow(() -> new IllegalArgumentException(name+ "의 회원은 없습니다."));
    }

}
