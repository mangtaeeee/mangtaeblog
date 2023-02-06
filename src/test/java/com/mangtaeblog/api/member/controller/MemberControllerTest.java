package com.mangtaeblog.api.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.request.UserLogin;
import com.mangtaeblog.api.post.domain.Post;
import com.mangtaeblog.api.post.repository.PostRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 테스트")
    void 회원가입() throws Exception{

        //given
        MemberCreate member = MemberCreate.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .build();


        String json = objectMapper.writeValueAsString(member);
        //when
        mockMvc.perform(post("/api/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //then
        Member findOne = memberRepository.findAll().get(0);

        Assertions.assertEquals("홍길동",findOne.getUsername());

    }
    @Test
    @DisplayName("회원단건조회")
    void 단건조회() throws Exception {

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        Post post = Post.builder()
                .title("제목 입니다.")
                .content("내용 입니다.")
                .writer("작성자입니다")
                .member(member)
                .build();

        postRepository.save(post);

        //excepted
        mockMvc.perform(get("/api/member/{memberId}",member.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(member.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value("asd9658"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("홍길동"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그인 확인 테스트")
    void 로그인() throws Exception {
        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        UserLogin userLogin = UserLogin.builder()
                .userId("asd9658")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(userLogin);

        //excepted
        mockMvc.perform(post("/api/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}