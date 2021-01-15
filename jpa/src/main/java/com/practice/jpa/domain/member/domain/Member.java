package com.practice.jpa.domain.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
public class Member {
    
    @Id
    @SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 명
        initialValue = 1,
        allocationSize = 1
    )
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "NAME")
    private String name;

    // @Column(name = "AGE")
    private Integer age;

    @Builder
    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
