package com.self.webservice.web.dto.posts;

import com.self.webservice.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * `@NoArgsConstructor` 가 없으면 Api Test 시 RestTemplate 이 역직렬화를 실패한다.
 */
@Getter
@NoArgsConstructor
@ToString
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.createDate = entity.getCreateDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
