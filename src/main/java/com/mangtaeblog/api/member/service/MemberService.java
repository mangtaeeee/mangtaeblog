package com.mangtaeblog.api.member.service;

import com.mangtaeblog.api.exception.MemberNotFound;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.member.request.MemberCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(MemberCreate request) {

        Member member = Member.builder()
                .userId(request.getUserId())
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        return memberRepository.save(member);
    }

    public Member findOne(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFound());
        return Member.builder()
                .username(member.getUsername())
                .userId(member.getUserId())
                .email(member.getEmail())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }
}
