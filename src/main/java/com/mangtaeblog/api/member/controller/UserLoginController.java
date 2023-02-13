package com.mangtaeblog.api.member.controller;

import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserLoginController {

    private final MemberService memberService;

    @GetMapping("/auth/login")
    public String join() {
        return "/login/Login";
    }

    @PostMapping("/auth/joinProc")
    public String joinProc(@RequestBody @Valid MemberCreate member) {
        memberService.join(member);

        return "redirect:/auth/login";

    }
}
