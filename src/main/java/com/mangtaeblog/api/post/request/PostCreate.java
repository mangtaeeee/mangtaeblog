package com.mangtaeblog.api.post.request;

import com.mangtaeblog.api.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @param : 글 작성을 위한 클래스
 */
@Setter
@Getter @ToString
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해 주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    private String writer;

    private int view;

    @Builder
    public PostCreate(String title, String content, String writer, int view) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view;
    }

    public void validate() {
        if (title.contains("바보")) {
            throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
        }
    }


}
