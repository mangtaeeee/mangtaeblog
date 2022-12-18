package com.mangtaeblog.api.member.controller;

import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/join")
    public void join(@RequestBody MemberCreate member){
        memberService.join(member);
    }

    @GetMapping("/member/{memberId}")
    public void findOne(@PathVariable Long id){
        memberService.findOne(id);
    }
}
