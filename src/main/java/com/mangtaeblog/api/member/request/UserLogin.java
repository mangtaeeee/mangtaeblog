package com.mangtaeblog.api.member.request;

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
}
