package com.mangtaeblog.api.comment.domain;


import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.shared.domain.BasicTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "COMMENT_SEQ",
        initialValue = 1, allocationSize = 50)
public class Comment extends BasicTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GENERATOR")
    @Column(name = "comment_id")
    private Long id;


    @Column(nullable = false, length = 1000)
    private String content; // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>(); // 답글

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Builder
    public Comment(Long id, String content, Comment parent, Member member, Post post) {
        this.id = id;
        this.content = content;
        this.parent = parent;
        this.member = member;
        this.post = post;
    }

    public CommentEditor.CommentEditorBuilder toEditor() {
        return CommentEditor.builder()
                .content(content);
    }

    public void edit(CommentEditor commentEditor) {
        content = commentEditor.content();
    }


}
