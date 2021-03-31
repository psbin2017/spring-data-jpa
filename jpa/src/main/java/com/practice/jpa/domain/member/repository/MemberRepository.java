package com.practice.jpa.domain.member.repository;

import com.practice.jpa.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByName(String name);

    /**
     * JPQL
     */
    @Query("select m from Member as m where m.age= :age")
    List<Member> findByAge(@Param("age") Integer age);
}
