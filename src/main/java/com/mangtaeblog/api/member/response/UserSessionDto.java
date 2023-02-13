package com.mangtaeblog.api.member.response;

import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

    private String userId;
    private String password;
    private String username;
    private String email;
    private Role role;

    public UserSessionDto(Member member) {
        this.userId = member.getUserId();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
}
