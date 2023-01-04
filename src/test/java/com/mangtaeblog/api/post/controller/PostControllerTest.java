package com.mangtaeblog.api.post.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangtaeblog.api.comment.domain.Comment;
import com.mangtaeblog.api.comment.repository.CommentRepository;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.repository.PostRepository;
import com.mangtaeblog.api.post.request.PostCreate;
import com.mangtaeblog.api.post.request.PostEdit;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

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
        postRepository.deleteAll();
        memberRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts (post) 요청 시  저장한다")
    void 글작성() throws Exception {
        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        PostCreate postCreate = PostCreate.builder()
                .title("제목 입니다.")
                .content("내용 입니다.")
                .memberId(member.getId())
                .build();

        String json = objectMapper.writeValueAsString(postCreate);

        //when
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //then
        Assertions.assertEquals(1L,postRepository.count());

        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals("제목 입니다.",post.getTitle());
        Assertions.assertEquals("내용 입니다.",post.getContent());

    }

    @Test
    @DisplayName("/posts 요청시 title / content 값은 필수다")

    void 저장_예외() throws Exception {

        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        PostCreate postCreate = PostCreate.builder()
                .title("")
                .content("")
                .memberId(member.getId())
                .build();

        String json = objectMapper.writeValueAsString(postCreate);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("타이틀을 입력해 주세요."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.content").value("내용을 입력해 주세요."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts{postId} 요청 시 글 한개 조회")
    void 글한개조회() throws Exception {

        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목 입니다.")
                .content("내용 입니다.")
                .writer("작성자입니다")
                .member(member)
                .build();

        postRepository.save(post);

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content("내용입니다.")
                .build();
        commentRepository.save(comment);

        mockMvc.perform(get("/posts/{postId}",post.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(post.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목 입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("내용 입니다."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts?page=1&size=10 요청시 1페이지 10개 조회")
    void 글전체조회1() throws Exception {
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("제목 " + i)
                        .content("내용 " + i)
                        .writer("작성자입니다 " + i)
                        .member(member)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        mockMvc.perform(get("/posts?page=1&size=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("제목 19"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("내용 19"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts?page=0 으로 요청했을때 첫페이지를 가져온다")
    void 글전체조회2() throws Exception {

        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("제목 " + i)
                        .content("내용 " + i)
                        .writer("작성자입니다" + i)
                        .member(member)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        mockMvc.perform(get("/posts?page=1&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("제목 19"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("내용 19"))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("글 제목 수정")
    void 글제목수정() throws Exception {
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
                .title("제목 수정 전")
                .content("내용입니다.")
                .writer("작성자입니다")
                .member(member)
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정했습니다.")
                .content("내용입니다.")
                .writer("작성자입니다")
                .build();

        mockMvc.perform(patch("/posts/{postId}", post.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void 글삭제() throws Exception {
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
                .writer("작성자입니다")
                .member(member)
                .build();
        postRepository.save(post);
        //expected
        mockMvc.perform(delete("/posts/{postId}", post.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


}