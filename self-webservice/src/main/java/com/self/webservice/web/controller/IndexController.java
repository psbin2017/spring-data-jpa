package com.self.webservice.web.controller;

import com.self.webservice.global.config.auth.LoginUser;
import com.self.webservice.global.config.auth.dto.SessionUser;
import com.self.webservice.service.posts.PostsService;
import com.self.webservice.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping({ "/", "/index" })
    public String index(Model model,
                        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @LoginUser SessionUser sessionUser) {

        // Posts model
        Page<PostsResponseDto> posts = postsService.findAllByPaging( pageable );

        model.addAttribute("posts", posts);
        model.addAttribute("posts-previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("posts-next", pageable.next().getPageNumber());

        // Session model
        if ( sessionUser != null ) {
            model.addAttribute("userName", sessionUser.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        // View Resolver 가 /src/main/resources/{return}.mustache 로 반환한다.
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute( "posts", postsService.findById(id));
        return "posts-update";
    }

}
