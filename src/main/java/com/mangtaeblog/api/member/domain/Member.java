package com.mangtaeblog.api.member.domain;

import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.shared.domain.BasicTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50)
@NoArgsConstructor
@Getter
public class Member extends BasicTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(length = 30, unique = true)
    private String userId;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();

    @Builder
    public Member(String username, String userId, String email, String password, Role role) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
