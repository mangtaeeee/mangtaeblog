package com.mangtaeblog.api.comment.domain;

import com.mangtaeblog.api.exception.MangtaeBlogException;

public class CommentNotFound extends MangtaeBlogException {

    private static final String MESSAGE = "존재하지 않는 댓글입니다.";

    public CommentNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
