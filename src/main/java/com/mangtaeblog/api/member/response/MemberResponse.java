package com.mangtaeblog.api.member.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class MemberResponse {

    private final Long id;
    private final String username;
    private final String userId;
    private final String email;
    private final Enum role;
    private final String createDate;
    private final String updateDate;
}
