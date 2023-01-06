package com.mangtaeblog.api.member.domain;

import com.mangtaeblog.api.exception.MangtaeBlogException;

public class MemberInvalidSignInformation extends MangtaeBlogException {

    private static final String MESSAGE = "아이디/비밀번호가 올바르지 않습니다.";

    public MemberInvalidSignInformation() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
