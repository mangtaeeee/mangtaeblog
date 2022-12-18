package com.mangtaeblog.api.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 블로그 주인인 ADMIN은 한개만 나머지는 USER
 */
@RequiredArgsConstructor
@Getter
public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
