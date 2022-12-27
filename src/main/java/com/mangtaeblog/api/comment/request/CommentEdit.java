package com.mangtaeblog.api.comment.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter @Getter
public class CommentEdit {


    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Builder
    public CommentEdit(String content, LocalDateTime createDate, LocalDateTime updateDate) {
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
