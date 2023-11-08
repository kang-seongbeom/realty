package com.ssafy.realty.user.application.service;

import com.ssafy.realty.user.application.port.in.*;
import com.ssafy.realty.user.application.port.in.dto.RegistDto;
import com.ssafy.realty.user.application.port.in.dto.UpdateDto;
import com.ssafy.realty.user.application.port.out.CommandUserPort;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommandUserService implements CommandUserUseCase {

    private final CommandUserPort commandUserPort;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    @Override
    public void regist(RegistDto registDto) {
        UserDomain registRequestUserDomain =
                UserDomain.init(
                        registDto.getUsername(),
                        encoder.encode(registDto.getPassword()),
                        registDto.getNickname());
        commandUserPort.regist(registRequestUserDomain);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        commandUserPort.delete(id);
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
        commandUserPort.update(updateRequestUserDomain);
    }
}
