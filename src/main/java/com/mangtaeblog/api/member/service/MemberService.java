package com.mangtaeblog.api.member.service;

import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(Member request) {

        Member member = Member.builder()
                .userId(request.getUserId())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
        return memberRepository.save(member);
    }
}
