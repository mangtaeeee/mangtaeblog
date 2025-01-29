package com.mangtaeblog.api.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public record UserLogin(

        @NotBlank(message = "아이디를 입력해 주세요.")
        String userId,

        @NotBlank(message = "비밀번호를 입력해 주세요")
        String password

) {
}