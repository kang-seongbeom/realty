package com.ssafy.realty.user.adapter.in.web;

import com.ssafy.realty.security.config.SecurityConfig;
import com.ssafy.realty.user.application.port.in.DeleteUserUseCase;
import com.ssafy.realty.user.application.port.in.RegistUserUseCase;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistUserUseCase registUserUseCase;

    @MockBean
    private DeleteUserUseCase deleteUserUseCase;



    @Test
    @DisplayName("회원가입")
    @WithMockUser
    public void regist() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "qkfka9045@gmail.com");
        requestBody.put("password", "1234");
        requestBody.put("nickname", "nick");

        doNothing().when(registUserUseCase).regist(any());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/regist")
                .with(csrf())
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());

    }

}