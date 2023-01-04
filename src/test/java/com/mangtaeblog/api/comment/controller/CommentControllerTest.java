package com.mangtaeblog.api.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangtaeblog.api.comment.domain.Comment;
import com.mangtaeblog.api.comment.repository.CommentRepository;
import com.mangtaeblog.api.comment.request.CommentCreate;
import com.mangtaeblog.api.comment.request.CommentEdit;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
        commentRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts/{postId}/comments 요청 시  저장한다")
    void 저장() throws Exception {

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        CommentCreate commentCreate = CommentCreate.builder()
                .content("댓글입니당")
                .memberId(member.getId())
                .build();
        //when

        String json = objectMapper.writeValueAsString(commentCreate);

        //then
        mockMvc.perform(post("/posts/{postId}/comments",post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts/{postId}/comments 요청 시  조회한다")
    void 조회() throws Exception {

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content("내용입니다.")
                .build();
        commentRepository.save(comment);

        //then
        mockMvc.perform(get("/posts/{postId}/comments",post.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts/{postId}/comments/{commentId} 요청 시 수정한다")
    void 수정() throws Exception {

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자입니다.")
                .member(member)
                .build();

        postRepository.save(post);

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content("내용입니다.")
                .build();
        commentRepository.save(comment);

        //when
        CommentEdit commentEdit = CommentEdit.builder()
                .content("수정했습니다.")
                .build();

        //then
        mockMvc.perform(patch("/posts/{postId}/comments/{commentId}",post.getId(),comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentEdit)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }


}