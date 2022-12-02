package com.mangtaeblog.api.controller;

import com.mangtaeblog.api.domain.Post;
import com.mangtaeblog.api.request.PostCreate;
import com.mangtaeblog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Post post(@RequestBody @Valid PostCreate postCreate) {
        postCreate.validate();
        return postService.write(postCreate);
    }
}
