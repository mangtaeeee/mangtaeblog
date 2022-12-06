package com.mangtaeblog.api.post.controller;

import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.response.PostResponse;
import com.mangtaeblog.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/posts/{postId}")
    public PostResponse findOne(@PathVariable Long postId) {
        return postService.findOne(postId);
    }

    @PostMapping("/posts")
    public Post post(@RequestBody @Valid PostCreate postCreate) {
        postCreate.validate();
        return postService.write(postCreate);
    }


}
