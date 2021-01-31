package com.self.webservice.web.controller;

import com.self.webservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * `@RestController` 는 반환 타입을 JSON 객체로 반환하게 한다.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/helloDto")
    public HelloResponseDto helloDto(
            @RequestParam("name") final String name,
            @RequestParam("amount") final int amount) {

        return new HelloResponseDto(name, amount);
    }

}
