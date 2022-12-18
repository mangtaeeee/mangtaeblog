package com.mangtaeblog.api.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.member.request.MemberCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //then
        Member findOne = memberRepository.findAll().get(0);
        Assertions.assertEquals("홍길동",findOne.getUsername());
    }
}