package com.mangtaeblog.api.comment.response;

import com.mangtaeblog.api.comment.domain.Comment;
import lombok.Builder;

import java.util.List;

@Builder
public record CommentResponse(Long id, String content, String userId, Long postId,
                              String createDate, String updateDate, List<CommentResponse> comments) {

    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getMember().getUserId())
                .postId(comment.getPost().getId())
                .createDate(comment.getCreatedDate())
                .updateDate(comment.getModifiedDate())
                .comments(List.of())  // 자식 댓글 처리 가능
                .build();
    }
}