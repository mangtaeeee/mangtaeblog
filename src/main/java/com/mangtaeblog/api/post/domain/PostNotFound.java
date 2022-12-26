package com.mangtaeblog.api.post.domain;

import com.mangtaeblog.api.exception.MangtaeBlogException;

public class PostNotFound extends MangtaeBlogException {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
