package com.mangtaeblog.api.post.controller;

import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.request.PostSearch;
import com.mangtaeblog.api.post.response.PostResponse;
import com.mangtaeblog.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/posts/{postId}")
    public PostResponse findOne(@PathVariable Long postId) {
        return postService.findOne(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> postList(@ModelAttribute PostSearch postSearch) {
        return postService.findAll(postSearch);
    }



}