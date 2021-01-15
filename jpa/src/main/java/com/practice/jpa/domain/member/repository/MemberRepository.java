package com.practice.jpa.domain.member.repository;

import com.practice.jpa.domain.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByName(String name);
}
