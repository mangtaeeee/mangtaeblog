package com.mangtaeblog.api.post.domain;

import com.mangtaeblog.api.comment.domain.Comment;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.shared.domain.BasicTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "POST_SEQ_GENERATOR",
        sequenceName = "POST_SEQ",
        initialValue = 1, allocationSize = 50)
public class Post extends BasicTimeEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ_GENERATOR")
    private Long id;

    @Column(length = 500)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @OneToMany(mappedBy = "post",orphanRemoval = true, fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    private List<Comment> comments;

    @Builder
    public Post(String title, String content, String writer, int view, Member member, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view;
        this.member = member;
        this.comments = comments;
    }

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
