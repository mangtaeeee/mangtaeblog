package com.mangtaeblog.api.comment.response;

import com.mangtaeblog.api.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponse {

    private Long id;
    private String content;
    private String userId;
    private Long postId;

    private String createDate;

    private String updateDate;

    private List<CommentResponse> comments = new ArrayList<>();

    @Builder
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getMember().getUserId();;
        this.postId = comment.getPost().getId();
        this.createDate = comment.getCreatedDate();
        this.updateDate = comment.getModifiedDate();

    }
}
