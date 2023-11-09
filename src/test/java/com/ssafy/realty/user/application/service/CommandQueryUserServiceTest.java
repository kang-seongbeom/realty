package com.ssafy.realty.user.application.service;

import com.ssafy.realty.user.application.port.in.dto.RegistDto;
import com.ssafy.realty.user.application.port.in.dto.UpdateDto;
import com.ssafy.realty.user.application.port.out.CommandUserPort;
import com.ssafy.realty.user.application.port.out.QueryUserPort;
import com.ssafy.realty.user.domain.UserDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("서비스 단위 테스트")
@ExtendWith(MockitoExtension.class)
class CommandQueryUserServiceTest {

    @InjectMocks
    private QueryUserService queryUserService;

    private CommandUserService commandUserService;

    @Mock
    private QueryUserPort queryUserPort;

    @Mock
    private CommandUserPort commandUserPort;

    @InjectMocks
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    public void setCommandUserService(){
        commandUserService = new CommandUserService(commandUserPort, encoder);
    }

    @Test
    @DisplayName("회원 가입")
    public void regist(){
        // given
        RegistDto dto = RegistDto
                .builder()
                .username("qkfka9045@gmail.com")
                .password("a1234567")
                .nickname("nick")
                .build();
        doNothing().when(commandUserPort).regist(any());

        // when
        commandUserService.regist(dto);

        // then
        verify(commandUserPort).regist(any());
    }

    @Test
    @DisplayName("회원 탈퇴")
    public void delete(){
        // given
        Long id = 1L;

        doNothing().when(commandUserPort).delete(id);

        // when
        commandUserPort.delete(id);

        // then
        verify(commandUserPort).delete(id);
    }

    @Test
    @DisplayName("회원 정보 수정")
    public void update(){
        // given
        UpdateDto updateDto = UpdateDto
                .builder()
                .id(1L)
                .password("a1234567")
                .nickname("nick")
                .build();
        doNothing().when(commandUserPort).update(any());

        // when
        commandUserService.update(updateDto);

        // then
        verify(commandUserPort).update(any());
    }

    @Test
    @DisplayName("회원 정보 조회")
    public void query(){
        // given
        String username = "qkfka9045@gmail.com";
        UserDomain userDomain = UserDomain.init(null, "qkfka9045@gmail.com", encoder.encode("a1234567"), "nick");

        when(queryUserPort.query(username)).thenReturn(userDomain);

        // when
        queryUserService.query(username);

        // then
        verify(queryUserPort).query(username);
    }
}