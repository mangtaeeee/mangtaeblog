package com.mangtaeblog.api.comment.controller;

import com.mangtaeblog.api.comment.request.CommentCreate;
import com.mangtaeblog.api.comment.request.CommentEdit;
import com.mangtaeblog.api.comment.response.CommentResponse;
import com.mangtaeblog.api.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity save(@PathVariable Long postId, @RequestBody @Valid CommentCreate commentCreate) {
        return ResponseEntity.ok(commentService.save(postId,commentCreate));
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentResponse> findComments(@PathVariable Long postId) {
        return commentService.findAll(postId);
    }


    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity update(@PathVariable Long commentId, @RequestBody @Valid CommentEdit commentEdit) {
        commentService.update(commentId, commentEdit);
        return ResponseEntity.ok(commentEdit);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok(commentId);
    }
}
