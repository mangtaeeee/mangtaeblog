package com.mangtaeblog.api.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserLogin {

    @NotBlank(message = "아이디를 입력해 주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;

    @Builder
    public UserLogin(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
