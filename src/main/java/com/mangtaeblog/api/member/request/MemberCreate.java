package com.mangtaeblog.api.member.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCreate {

    private Long id;

    @NotBlank(message = "회원명을 입력해 주세요.")
    private String username;

    @NotBlank(message = "아이디를 입력해 주세요.")
    private String userId;

    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;
    private String password;
}
