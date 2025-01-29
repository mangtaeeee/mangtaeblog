package com.mangtaeblog.api.post.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

public record PostEdit(
        @NotBlank(message = "타이틀을 입력해 주세요.")
        String title,

        @NotBlank(message = "내용을 입력해 주세요.")
        String content,

        String writer,
        int view,
        LocalDateTime createDate,
        LocalDateTime updateDate
) {

}
