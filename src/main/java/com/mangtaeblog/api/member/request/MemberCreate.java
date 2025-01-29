package com.mangtaeblog.api.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


public record MemberCreate(

        Long id,

        @NotBlank(message = "회원명을 입력해 주세요.")
        String username,

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,20}$", message = "아이디는 특수문자를 제외한 4~20자리여야 합니다.")
        @NotBlank(message = "아이디를 입력해 주세요.")
        String userId,

        @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일을 입력해 주세요.")
        String email,

        String password

) {

}
