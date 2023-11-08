package com.ssafy.realty.user.application.port.in;

import com.ssafy.realty.user.application.port.in.dto.QueryResponseDto;

public interface QueryUserUseCase {
    public QueryResponseDto query(String username);
}
