package com.mangtaeblog.api.member.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCreate {

    private Long id;

    @NotBlank(message = "회원명을 입력해 주세요.")
    private String username;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,20}$", message = "아이디는 특수문자를 제외한 4~20자리여야 합니다.")
    @NotBlank(message = "아이디를 입력해 주세요.")
    private String userId;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;
    private String password;





}
