package com.self.webservice.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @BeforeEach
    public void init() {
        String title = "이런 엔딩";
        String author = "IU";
        String content = "나 같은 누군가에게 사랑 받게 될까";

        Posts savePosts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postsRepository.save(savePosts);
    }

    @DisplayName("게시글을 저장하고 작성자로 조회하여 내용이 같은지 확인한다.")
    @Test
    public void test1() {
        // given
        String title = "소녀";
        String author = "오혁";
        String content = "노을 진 창가에 앉아";

        Posts savePosts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postsRepository.save(savePosts);

        // when
        List<Posts> findPostsList = postsRepository.findByAuthor(author);

        // then
        Posts targetPosts = findPostsList.get(0);
        assertEquals(content, targetPosts.getContent());
    }

    @DisplayName("작성자로 게시글을 조회한다.")
    @Test
    public void test2() {
        // given
        String author = "IU";

        // when
        List<Posts> findPostsList = postsRepository.findByAuthor(author);

        // then
        assertFalse(findPostsList.isEmpty());
    }

    @DisplayName("작성자로 게시글을 삭제한다.")
    @Test
    public void test3() {
        // given
        String author = "IU";

        // when
        postsRepository.deleteByAuthor(author);
        List<Posts> emptyPostsList = postsRepository.findByAuthor(author);

        // then
        assertTrue(emptyPostsList.isEmpty());
    }
}
