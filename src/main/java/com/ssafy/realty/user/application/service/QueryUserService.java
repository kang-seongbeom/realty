package com.ssafy.realty.user.application.service;

import com.ssafy.realty.user.application.port.in.QueryUserUseCase;
import com.ssafy.realty.user.application.port.in.dto.QueryResponseDto;
import com.ssafy.realty.user.application.port.out.QueryUserPort;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class QueryUserService implements QueryUserUseCase {

    private final QueryUserPort queryUserPort;

    @Override
    public QueryResponseDto query(String username) {
        UserDomain userDomain = queryUserPort.query(username);
        QueryResponseDto queryResponseDto = QueryResponseDto
                .builder()
                .id(userDomain.getUserDomainId().getValue())
                .username(userDomain.getUserDomainData().getUsername())
                .password(userDomain.getUserDomainData().getPassword())
                .nickname(userDomain.getUserDomainData().getNickname())
                .role(userDomain.getUserDomainData().getRole())
                .build();

        return queryResponseDto;
    }
}
