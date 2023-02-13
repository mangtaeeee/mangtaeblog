package com.mangtaeblog.api.member.repository;

import com.mangtaeblog.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    //Security
    Optional<Member> findByUserIdAndPassword(String userId, String password);

    Optional<Member> findByUserId(String userId);



}
