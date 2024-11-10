package com.mangtaeblog.api.member.response;

import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

    private final String userId;
    private final  String password;
    private final String username;
    private final String email;
    private final Role role;

    public UserSessionDto(Member member) {
        this.userId = member.getUserId();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
}
