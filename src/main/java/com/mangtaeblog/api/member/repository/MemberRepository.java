package com.mangtaeblog.api.member.repository;

import com.mangtaeblog.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
