package com.mangtaeblog.api.post.domain;

import com.mangtaeblog.api.shared.domain.BasicTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //매개 변수가 없는 기본생성자
@Builder @AllArgsConstructor
public class Post extends BasicTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Lob
    private String content;

    private String writer;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int view;

    public PostEditor.PostEditorBuilder toEditor() {
        return PostEditor.builder()
                .title(title)
                .content(content);
    }

    public void edit(PostEditor postEditor) {
        title = postEditor.getTitle();
        content = postEditor.getContent();
    }
}
