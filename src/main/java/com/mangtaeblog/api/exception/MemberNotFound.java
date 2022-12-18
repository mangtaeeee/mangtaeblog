package com.mangtaeblog.api.exception;

public class MemberNotFound extends MangtaeBlogException{

    private static final String MESSAGE = "존재하지 않는 회원 입니다.";

    public MemberNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
