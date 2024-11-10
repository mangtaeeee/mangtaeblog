package com.mangtaeblog.api.post.controller;

import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.request.PostEdit;
import com.mangtaeblog.api.post.response.PostResponse;
import com.mangtaeblog.api.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;


    @PostMapping("/posts/post")
    public ResponseEntity<Void> post(@RequestBody @Valid PostCreate postCreate) {
        postCreate.validate();
        postService.write(postCreate);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/read/{postId}")
    public ResponseEntity<PostResponse> findOne(@PathVariable Long postId) {
        postService.updateView(postId);
        return ResponseEntity.ok(postService.findOne(postId));
    }

    @GetMapping("/posts/list")
    public Page<PostResponse> postList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.findAllPaging(pageable);
    }

    @PatchMapping("/posts/edit/{postId}")
    public ResponseEntity<Void> edit(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit){
        postService.edit(postId,postEdit);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/delete/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }






}
