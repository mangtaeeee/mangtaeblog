package com.mangtaeblog.api.member.controller;

import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.response.MemberResponse;
import com.mangtaeblog.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/join")
    public ResponseEntity join(@RequestBody MemberCreate member){
        return ResponseEntity.ok(memberService.join(member));
    }

    @GetMapping("/member/{memberId}")
    public MemberResponse findOne(@PathVariable Long memberId){
        return memberService.findOne(memberId);
    }


}
