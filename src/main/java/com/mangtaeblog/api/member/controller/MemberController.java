package com.mangtaeblog.api.member.controller;

import com.mangtaeblog.api.member.request.MemberCreate;
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
    public ResponseEntity findOne(@PathVariable Long memberId){
        return ResponseEntity.ok(memberService.findOne(memberId));
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity delete(@PathVariable Long memberId) {
        memberService.secession(memberId);
        return ResponseEntity.ok(memberId);
    }

}
