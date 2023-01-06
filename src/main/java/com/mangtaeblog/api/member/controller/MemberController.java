package com.mangtaeblog.api.member.controller;

import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.request.UserLogin;
import com.mangtaeblog.api.member.request.UserSession;
import com.mangtaeblog.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/foo")
    public Long foo(UserSession userSession) {
        log.info("{}", userSession.id);
        return userSession.id;
    }

    @PostMapping("/member/login")
    public void login(@RequestBody UserLogin userLogin){
        // json에서 아이디 비번 받아서
        log.info(">>>login={}", userLogin);
        // DB에서 조회
        memberService.singin(userLogin);
        // 토큰을 응답

    }

    @PostMapping("/member/join")
    public ResponseEntity join(@RequestBody @Valid MemberCreate member){
        memberService.join(member);
        return ResponseEntity.ok(member.getId());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity findOne(@PathVariable Long memberId){
        return ResponseEntity.ok(memberService.findOne(memberId));
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity delete(@PathVariable Long memberId) {
        memberService.secession(memberId);
        return ResponseEntity.ok(memberId);
    }

}
