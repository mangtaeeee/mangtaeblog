package com.mangtaeblog.api.post.response;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter @RequiredArgsConstructor
@Builder
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final int view;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;


}
