package com.self.webservice.web.controller;

import com.self.webservice.domain.posts.PostsRepository;
import com.self.webservice.web.dto.posts.PostsResponseDto;
import com.self.webservice.web.dto.posts.PostsSaveRequestDto;
import com.self.webservice.web.dto.posts.PostsUpdateRequestDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * `@WebMvcTest` 는 JPA 기능이 동작하지 않는다.
 */
// @WebMvcTest(controllers = HelloController.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("local")
public class PostApiTest {

    @LocalServerPort
    private int port;

    @Value("${custom.this.api.url}")
    private String url;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void teardown() {
        postsRepository.deleteAll();
    }

    @DisplayName("게시글을 저장한다.")
    @ParameterizedTest
    @CsvSource({
            "Cereal, Crush, 호랑이 기운이 솟아나 지금",
            "4AM, IU, 일어난건지 아직 잠들지 못한건지",
            "그대라는시, 태연, 언제부터인지 그대를 보면"
    })
    public void PostsApi_saveParameterized_byCsvSource(String title, String content, String author) {
        // given
        String apiUrl = url + ":" + port + "/api/v1/posts";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(apiUrl, requestDto, Long.class);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody(), greaterThan(0L));
    }

    @DisplayName("게시글을 저장하고 조회한다.")
    @ParameterizedTest
    @MethodSource("ParameterizedTest_ByMethodSource")
    public void PostsApi_saveAndFindByIdParameterized_byMethodSource(String title, String content, String author) {
        // given
        String saveApiUrl = url + ":" + port + "/api/v1/posts";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        ResponseEntity<Long> saveResponseEntity = testRestTemplate.postForEntity(saveApiUrl, requestDto, Long.class);

        // when
        String findApiUrl = url + ":" + port + "/api/v1/posts/" + saveResponseEntity.getBody();

        ResponseEntity<PostsResponseDto> findByIdResponseEntity = testRestTemplate.getForEntity(findApiUrl, PostsResponseDto.class);

        // then
        assertEquals( HttpStatus.OK, findByIdResponseEntity.getStatusCode() );
        assertEquals(
                Objects.requireNonNull(findByIdResponseEntity.getBody()).getContent(), content
        );
    }

    @DisplayName("게시글을 수정한다.")
    @Test
    public void PostsApi_updateTest() {
        // given
        String saveApiUrl = url + ":" + port + "/api/v1/posts";

        String beforeTitle = "Any July";
        String beforeContent = "너만 내게로 온다면";
        String author = "헤이즈";

        PostsSaveRequestDto saveRequestDto = PostsSaveRequestDto.builder()
                .title(beforeTitle)
                .content(beforeContent)
                .author(author)
                .build();

        ResponseEntity<Long> saveResponseEntity = testRestTemplate.postForEntity(saveApiUrl, saveRequestDto, Long.class);

        // given update
        String updateApiUrl = url + ":" + port + "/api/v1/posts/" + saveResponseEntity.getBody();

        String afterTitle = "Jenga";
        String afterContent = "누가 시킨적도 없는데 탑을 쌓아 올렸네";

        PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
                .title(afterTitle)
                .content(afterContent)
                .build();

        HttpEntity<PostsUpdateRequestDto> updateRequestEntity = new HttpEntity<>(updateRequestDto);

        testRestTemplate.exchange(updateApiUrl, HttpMethod.PUT, updateRequestEntity, Long.class);

        // when
        String findApiUrl = url + ":" + port + "/api/v1/posts/" + saveResponseEntity.getBody();

        ResponseEntity<PostsResponseDto> findByIdResponseEntity = testRestTemplate.getForEntity(findApiUrl, PostsResponseDto.class);

        // then
        assertEquals( HttpStatus.OK, findByIdResponseEntity.getStatusCode() );
        assertEquals(
                Objects.requireNonNull(findByIdResponseEntity.getBody()).getContent(), afterContent
        );
    }

    /**
     * `@ParameterizedTest` 에서 `@MethodSource` 로 사용하는 파라미터를 반환한다.
     * @return 작성된 Arguments
     */
    private static Stream<Arguments> ParameterizedTest_ByMethodSource() {
        return Stream.of(
                Arguments.of("Happy", "태연", "계절이 번져와 두드린 꿈을 꿔"),
                Arguments.of("마음", "IU", "쿵 내려앉으면 그건 너")
        );
    }
}
