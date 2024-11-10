package com.mangtaeblog.api.post.request;

import com.mangtaeblog.api.exception.InvalidRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


/**
 * @param : 글 작성을 위한 클래스
 */
@Setter
@Getter @AllArgsConstructor @NoArgsConstructor
@Builder
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해 주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    private Long memberId;


    public void validate() {
        if (title.contains("바보")) {
            throw new InvalidRequest("title", "제목에 바보를 포함할 수 없습니다.");
        }
    }


}
