package com.mangtaeblog.api.comment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Builder
@Getter
public class CommentCreate {

    @NotBlank(message = "내용을 입력해 주세요.")
    private final String content;
    private final Long memberId;

}
