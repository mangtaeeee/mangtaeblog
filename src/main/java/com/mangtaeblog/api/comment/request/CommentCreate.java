package com.mangtaeblog.api.comment.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class CommentCreate {

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;
    private Long memberId;

}
