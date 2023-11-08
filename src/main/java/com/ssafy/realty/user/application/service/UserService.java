package com.ssafy.realty.user.application.service;

import com.ssafy.realty.user.application.port.in.DeleteUserUseCase;
import com.ssafy.realty.user.application.port.in.QueryUserUseCase;
import com.ssafy.realty.user.application.port.in.RegistUserUseCase;
import com.ssafy.realty.user.application.port.in.UpdateUserUseCase;
import com.ssafy.realty.user.application.port.in.dto.QueryResponseDto;
import com.ssafy.realty.user.application.port.in.dto.RegistDto;
import com.ssafy.realty.user.application.port.in.dto.UpdateDto;
import com.ssafy.realty.user.application.port.out.DeleteUserPort;
import com.ssafy.realty.user.application.port.out.QueryUserPort;
import com.ssafy.realty.user.application.port.out.RegistUserPort;
import com.ssafy.realty.user.application.port.out.UpdateUserPort;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements RegistUserUseCase, DeleteUserUseCase, UpdateUserUseCase, QueryUserUseCase {

    private final QueryUserPort queryUserPort;
    private final RegistUserPort registUserPort;
    private final DeleteUserPort deleteUserPort;
    private final UpdateUserPort updateUserPort;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    @Override
    public void regist(RegistDto registDto) {
        UserDomain registRequestUserDomain =
                UserDomain.init(
                        registDto.getUsername(),
                        encoder.encode(registDto.getPassword()),
                        registDto.getNickname());
        registUserPort.regist(registRequestUserDomain);
    }

    @Override
    public QueryResponseDto query(String username) {
        UserDomain userDomain = queryUserPort.query(username);
        QueryResponseDto queryResponseDto = QueryResponseDto
                .builder()
                .id(userDomain.getUserDomainId().getValue())
                .username(userDomain.getUserDomainData().getUsername())
                .password(userDomain.getUserDomainData().getPassword())
                .role(userDomain.getUserDomainData().getRole())
                .build();

        return queryResponseDto;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        deleteUserPort.delete(id);
    }

    @Transactional
    @Override
    public void update(UpdateDto updateDto) {
        UserDomain updateRequestUserDomain =
                UserDomain.init(
                        updateDto.getId(),
                        null,
                        encoder.encode(updateDto.getPassword()),
                        updateDto.getNickname());
        updateUserPort.update(updateRequestUserDomain);
    }
}
