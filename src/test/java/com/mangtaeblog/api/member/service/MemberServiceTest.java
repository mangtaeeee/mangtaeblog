package com.mangtaeblog.api.member.service;

import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    void 회원가입(){

        //given
        Member member = Member.builder()
                .userId("osp9658")
                .nickname("아무개")
                .password("123123")
                .email("osp9658@naver.com")
                .build();
        //when
        memberService.join(member);

        //then
        Assertions.assertEquals(1L, memberRepository.count());
        Member user = memberRepository.findAll().get(0);
        Assertions.assertEquals("osp9658",user.getUserId());
        Assertions.assertEquals("아무개",user.getNickname());

    }

}