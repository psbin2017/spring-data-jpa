package com.practice.jpa.domain.member.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApi {

    @GetMapping("/member")
    public String member() {
        return "member";
    }
}
