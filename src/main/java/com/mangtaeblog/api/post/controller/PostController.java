package com.mangtaeblog.api.post.controller;

import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.request.PostEdit;
import com.mangtaeblog.api.post.request.PostSearch;
import com.mangtaeblog.api.post.response.PostResponse;
import com.mangtaeblog.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/posts")
    public ResponseEntity post(@RequestBody @Valid PostCreate postCreate) {
        postCreate.validate();
        postService.write(postCreate);
       return ResponseEntity.ok(postCreate);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> findOne(@PathVariable Long postId) {
        postService.updateView(postId);
        return ResponseEntity.ok(postService.findOne(postId));
    }

    @GetMapping("/posts")
    public List<PostResponse> postList(@ModelAttribute PostSearch postSearch) {
        return postService.findAll(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity edit(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit){
        postService.edit(postId,postEdit);
        return ResponseEntity.ok(postId);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok(postId);
    }






}
