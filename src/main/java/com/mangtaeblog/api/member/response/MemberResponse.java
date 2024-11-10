package com.mangtaeblog.api.member.response;

import com.mangtaeblog.api.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
public record MemberResponse(
        Long id,
        String username,
        String userId,
        String email,
        Role role,
        String createDate,
        String updateDate) {

}
