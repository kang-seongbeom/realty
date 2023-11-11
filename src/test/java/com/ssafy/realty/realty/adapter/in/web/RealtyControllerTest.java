package com.ssafy.realty.realty.adapter.in.web;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("부동산 컨트롤러 통합 테스트")
class RealtyControllerTest extends RealtyJsonData{

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("마커 주변 집 정보")
    public void realtyInfoTest() throws Exception {
        JSONObject requestBody = realtyInfoJsonBody();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/realty/realty-info")
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

}
