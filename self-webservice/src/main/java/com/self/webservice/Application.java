package com.self.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 애플리케이션 클래스는 항상 프로젝트 상단에 위치해야 한다.
 * `@EnableJpaAuditing` : {@link com.self.webservice.global.domain.BaseTimeEntity}
 */
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
