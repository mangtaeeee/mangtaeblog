//package com.mangtaeblog.api.member.service;
//
//import com.mangtaeblog.api.member.domain.Member;
//import com.mangtaeblog.api.member.domain.MemberNotFound;
//import com.mangtaeblog.api.member.repository.MemberRepository;
//import com.mangtaeblog.api.member.response.UserSessionDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpSession;
//
//@RequiredArgsConstructor
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//    private final HttpSession session;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByUserId(username).orElseThrow(
//                () -> new MemberNotFound());
//
//        session.setAttribute("member", new UserSessionDto(member));
//
//        /* 시큐리티 세션에 유저 정보 저장 */
//        return new DefaultUserDetails(member);
//    }
//}
