package com.mangtaeblog.api.comment.controller;

import com.mangtaeblog.api.comment.request.CommentCreate;
import com.mangtaeblog.api.comment.request.CommentEdit;
import com.mangtaeblog.api.comment.response.CommentResponse;
import com.mangtaeblog.api.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> save(@PathVariable Long postId, @RequestBody @Valid CommentCreate commentCreate) {
        commentService.save(postId,commentCreate);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> findComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findAll(postId));
    }


    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Void> update(@PathVariable Long commentId, @RequestBody @Valid CommentEdit commentEdit) {
        commentService.update(commentId, commentEdit);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }
}
