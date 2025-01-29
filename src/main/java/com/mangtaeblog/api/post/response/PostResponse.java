package com.mangtaeblog.api.post.response;


import com.mangtaeblog.api.comment.response.CommentResponse;
import com.mangtaeblog.api.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
public record PostResponse(
        Long id,
        String title,
        String content,
        String writer,
        int view,
        String createDate,
        String updateDate,
        List<CommentResponse> comments) {

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .view(post.getView())
                .createDate(post.getCreatedDate())
                .updateDate(post.getModifiedDate())
                .comments(post.getComments().stream()
                        .map(CommentResponse::of)
                        .toList())
                .build();
    }
}