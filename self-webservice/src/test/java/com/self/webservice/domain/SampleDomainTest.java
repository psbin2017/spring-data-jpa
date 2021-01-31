package com.self.webservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SampleDomainTest {

    // import static org.junit.jupiter.api.Assertions.*;

    @AfterEach
    public void cleanup() {
        // 테스트 실행 이후 처리를 작성합니다.
    }

//    @DisplayName("테스트 명을 입력하세요")
//    @Test
//    @Order(1)
//    public void test1() {
//        // 테스트를 작성합니다.
//    }
}
