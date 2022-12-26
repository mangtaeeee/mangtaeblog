package com.mangtaeblog.api.member.controller;

import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

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
