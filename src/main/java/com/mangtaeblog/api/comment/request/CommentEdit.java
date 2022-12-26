package com.mangtaeblog.api.comment.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter
public class CommentEdit {

    private Long id;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

}
