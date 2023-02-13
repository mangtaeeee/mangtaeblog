package com.mangtaeblog.api.member.service;

import com.mangtaeblog.api.member.domain.MemberInvalidSignInformation;
import com.mangtaeblog.api.member.domain.MemberNotFound;
import com.mangtaeblog.api.member.domain.Member;
import com.mangtaeblog.api.member.domain.Role;
import com.mangtaeblog.api.member.repository.MemberRepository;
import com.mangtaeblog.api.member.request.MemberCreate;
import com.mangtaeblog.api.member.request.UserLogin;
import com.mangtaeblog.api.member.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    public Member join(MemberCreate request) {

        Member member = Member.builder()
                .userId(request.getUserId())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        return memberRepository.save(member);
    }

    public MemberResponse singin(UserLogin userLogin) {

        Member member = memberRepository.findByUserIdAndPassword(userLogin.getUserId(), userLogin.getPassword())
                .orElseThrow(() -> new MemberInvalidSignInformation());

        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .userId(member.getUserId())
                .email(member.getEmail())
                .role(member.getRole())
                .build();
    }

    public MemberResponse findOne(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFound());

        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .userId(member.getUserId())
                .email(member.getEmail())
                .createDate(member.getCreatedDate())
                .updateDate(member.getModifiedDate())
                .role(member.getRole())
                .build();
    }

    public void secession(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFound());

        memberRepository.delete(member);
    }
}
