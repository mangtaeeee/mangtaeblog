package com.mangtaeblog.api.comment.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Builder
@Getter
public class CommentCreate {

    @NotBlank(message = "내용을 입력해 주세요.")
    private final String content;
    private final Long memberId;

}
