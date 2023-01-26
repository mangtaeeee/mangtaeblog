package com.mangtaeblog.api.post.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mangtaeblog.api.comment.response.CommentResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponse {


    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final int view;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime updateDate;
    private List<CommentResponse>  comments = new ArrayList<>();

    @Builder
    public PostResponse(Long id, String title, String content, String writer, int view, LocalDateTime createDate, LocalDateTime updateDate,List<CommentResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.comments = comments;
    }


}
