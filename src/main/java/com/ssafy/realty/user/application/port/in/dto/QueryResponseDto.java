package com.ssafy.realty.user.application.port.in.dto;

import com.ssafy.realty.common.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QueryResponseDto {
    Long id;
    String username;
    String password;
    String nickname;
    Role role;
}
