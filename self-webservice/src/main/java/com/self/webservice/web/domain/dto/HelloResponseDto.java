package com.self.webservice.web.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * `@RequiredArgsConstructor` 는 final 이 있는 필드는 생성자에 반드시 포함된다.
 */
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
