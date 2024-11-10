package com.mangtaeblog.api.comment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public record CommentEdit(
        @NotBlank(message = "내용을 입력해 주세요.") String content,
        LocalDateTime createDate,
        LocalDateTime updateDate) {


    @Builder
    public CommentEdit(String content, LocalDateTime createDate, LocalDateTime updateDate) {
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
