package com.mangtaeblog.api.comment.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mangtaeblog.api.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponse {

    private Long id;
    private String content;
    private String userId;
    private Long postId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updateDate;

    private List<CommentResponse> comments = new ArrayList<>();

    @Builder
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getMember().getUserId();;
        this.postId = comment.getPost().getId();
        this.createDate = comment.getCreateDate();
        this.updateDate = comment.getUpdateDate();

    }
}
