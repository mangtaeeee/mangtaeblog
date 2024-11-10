package com.mangtaeblog.api.post.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Param: 글 수정을 위한 클래스
 * 기능이 다르기에 PostCreate 와 같아도 따로 나눠야함
 */
@Setter
@Getter @ToString
public class PostEdit {

    @NotBlank(message = "타이틀을 입력해 주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    private String writer;
    private int view;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Builder
    public PostEdit(String title, String content, String writer, int view, LocalDateTime createDate, LocalDateTime updateDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
