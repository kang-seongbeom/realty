package com.ssafy.realty.user.adapter.in.web;

import com.ssafy.realty.common.Role;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import com.ssafy.realty.security.config.jwt.JwtManager;
import com.ssafy.realty.security.entity.User;
import com.ssafy.realty.security.repository.UserRepository;
import com.ssafy.realty.user.application.port.in.DeleteUserUseCase;
import com.ssafy.realty.user.application.port.in.RegistUserUseCase;
import com.ssafy.realty.user.application.port.in.UpdateUserUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("시큐리티 및 컨트롤러 통합 테스트")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private RegistUserUseCase registUserUseCase;

    @MockBean
    private DeleteUserUseCase deleteUserUseCase;

    @MockBean
    private UpdateUserUseCase updateUserUseCase;

    @MockBean
    private UserRepository userRepository;

    private String getAuthorizedUserToken(User saved) {
        PrincipalDetails principalDetails = new PrincipalDetails(saved);

        String accessToken = jwtManager.createAccessToken(principalDetails);

        if (accessToken != null && jwtManager.isTokenValid(accessToken)) {
            Authentication authentication = jwtManager.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return accessToken;
    }

    @Test
    @DisplayName("회원가입")
    public void regist() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "qkfka9045@gmail.com");
        requestBody.put("password", "a1234567");
        requestBody.put("nickname", "nick");

        doNothing().when(registUserUseCase).regist(any());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/regist")
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("로그인")
    public void login() throws Exception {
        // given
        User user = new User(null, "qkfka9045@gmail.com", "a1234567", Role.USER);

        JSONObject requestBody = new JSONObject();
        requestBody.put("username", user.getUsername());
        requestBody.put("password", user.getPassword());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/login")
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(new User(
                        null,
                        user.getUsername(),
                        bCryptPasswordEncoder.encode(user.getPassword()),
                        user.getRole()));

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(header().exists("accessToken"));
    }

    @Test
    @DisplayName("회원 정보 수정")
    public void update() throws Exception {
        // given
        User user = new User(1L, "qkfka9045@gmail.com", "a1234567", Role.USER);

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(new User(
                        user.getId(),
                        user.getUsername(),
                        bCryptPasswordEncoder.encode(user.getPassword()),
                        user.getRole()));

        doNothing().when(updateUserUseCase).update(any());

        String accessToken = getAuthorizedUserToken(user);

        JSONObject requestBody = new JSONObject();
        requestBody.put("password", "b1234567");
        requestBody.put("nickname", "updateNick");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch("/api/v1/user/update")
                .header("accessToken", accessToken)
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("회원 삭제")
    public void delete() throws Exception {
        // given
        User user = new User(1L, "qkfka9045@gmail.com", "a1234567", Role.USER);

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(new User(
                        user.getId(),
                        user.getUsername(),
                        bCryptPasswordEncoder.encode(user.getPassword()),
                        user.getRole()));

        doNothing().when(deleteUserUseCase).delete(user.getId());

        String accessToken = getAuthorizedUserToken(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/api/v1/user/delete")
                .header("accessToken", accessToken);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("검증할 수 없는 토큰 사용")
    public void unValidAccessToken() throws Exception {
        String unValidToken = "Bearer unValidAccessToken";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/api/v1/user/delete")
                .header("accessToken", unValidToken);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isInternalServerError());
    }
}
