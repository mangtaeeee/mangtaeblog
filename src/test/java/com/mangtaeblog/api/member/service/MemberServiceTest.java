package com.mangtaeblog.api.member.service;

import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.response.MemberResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void 회원가입(){

        //given
        MemberCreate member = MemberCreate.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .build();

        //when
        memberService.join(member);

        //then
        Assertions.assertEquals(1L, memberRepository.count());
        Member user = memberRepository.findAll().get(0);
        Assertions.assertEquals("asd9658",user.getUserId());

    }

    @Test
    @DisplayName("회원 단건 조회")
    void 회원1개_조회(){

        //given
        Member member = Member.builder()
                .userId("asd9658")
                .username("홍길동")
                .email("asd9658@naver.com")
                .password("1234")
                .build();

        //when
        memberRepository.save(member);
        MemberResponse response = memberService.findOne(member.getId());

        //then
        Assertions.assertEquals(member.getId(), response.getId());

    }


}