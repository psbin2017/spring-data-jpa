package com.self.webservice.web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// Junit4 @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloApi.class)
public class HelloApiTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("/hello :: hello 를 반환한다.")
    @Test
    public void test1() throws Exception {
        String hello = "hello";

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @DisplayName("/helloDto :: HelloResponseDto JSON 형으로 반환한다.")
    @Test
    public void test2() throws Exception {
        String name = "hello";
        int amount = 1000;

        mockMvc.perform(get("/helloDto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount) ))
                .andExpect( status().isOk() )
                // $ 기준으로 필드명을 명시한다.
                .andExpect( jsonPath("$.name", is(name)) )
                .andExpect( jsonPath("$.amount", is(amount)) );
    }

}
