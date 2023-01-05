package com.mangtaeblog.api.member.request;

public class UserSession {

    public final Long id;
    public final String userId;

    public UserSession(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }
}
