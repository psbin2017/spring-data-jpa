package com.self.webservice.service.posts;

import com.self.webservice.domain.posts.Posts;
import com.self.webservice.domain.posts.PostsRepository;
import com.self.webservice.web.dto.posts.PostsResponseDto;
import com.self.webservice.web.dto.posts.PostsSaveRequestDto;
import com.self.webservice.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(final PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts entity = beforeFindById(id);
        // 영속성 컨텍스트가 엔티티의 상태의 변경을 확인하기 때문에 엔티티의 값을 바꿔주면 된다.
        entity.update( requestDto.getTitle(), requestDto.getContent() );
        return id;
    }

    @Transactional
    public Long deleteById(Long id) {
        Posts entity = beforeFindById(id);
        postsRepository.deleteById(id);
        return id;
    }

    public PostsResponseDto findById(final Long id) {
        Posts entity = beforeFindById(id);
        return new PostsResponseDto(entity);
    }

    /**
     * 작업 이전에 게시글이 있는지 확인한다.
     * @param id 게시글 번호
     * @return 게시글, 없는 경우 예외를 던진다.
     */
    private Posts beforeFindById(final Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. \n id=" + id));
    }
}
