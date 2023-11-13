package com.ssafy.realty.realty.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("부동산 컨트롤러 통합 테스트")
class RealtyControllerTest extends RealtyJsonData{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("마커 주변 집 정보")
    public void realtyInfoTest() throws Exception {
        // given
        JSONObject requestBody = realtyInfoJsonBody();
        String expect = "{\"data\":[{\"type\":\"WALK\",\"time\":5,\"homeSummaryInfos\":[{\"aptCode\":47230000000010,\"apartmentName\":\"야사주공3차단지\",\"lat\":35.9768231457432,\"lng\":128.944137387794,\"address\":\"야사동 충효로 120\",\"totalDealAmount\":230,\"maxDealAmount\":6500,\"minDealAmount\":1800,\"avgDealAmount\":3.5130434782608697,\"avgArea\":38.86356521739134},{\"aptCode\":47230000000011,\"apartmentName\":\"야사5단지주공아파트\",\"lat\":35.9768231457432,\"lng\":128.944137387794,\"address\":\"야사동 충효로 120\",\"totalDealAmount\":114,\"maxDealAmount\":8500,\"minDealAmount\":4500,\"avgDealAmount\":5.692982456140351,\"avgArea\":53.3434210526315}]},{\"type\":\"WALK\",\"time\":10,\"homeSummaryInfos\":[{\"aptCode\":47230000000010,\"apartmentName\":\"야사주공3차단지\",\"lat\":35.9768231457432,\"lng\":128.944137387794,\"address\":\"야사동 충효로 120\",\"totalDealAmount\":230,\"maxDealAmount\":6500,\"minDealAmount\":1800,\"avgDealAmount\":3.5130434782608697,\"avgArea\":38.86356521739134},{\"aptCode\":47230000000011,\"apartmentName\":\"야사5단지주공아파트\",\"lat\":35.9768231457432,\"lng\":128.944137387794,\"address\":\"야사동 충효로 120\",\"totalDealAmount\":114,\"maxDealAmount\":8500,\"minDealAmount\":4500,\"avgDealAmount\":5.692982456140351,\"avgArea\":53.3434210526315}]},{\"type\":\"BYCYCLE\",\"time\":5,\"homeSummaryInfos\":[{\"aptCode\":47230000000010,\"apartmentName\":\"야사주공3차단지\",\"lat\":35.9768231457432,\"lng\":128.944137387794,\"address\":\"야사동 충효로 120\",\"totalDealAmount\":230,\"maxDealAmount\":6500,\"minDealAmount\":1800,\"avgDealAmount\":3.5130434782608697,\"avgArea\":38.86356521739134},{\"aptCode\":47230000000011,\"apartmentName\":\"야사5단지주공아파트\",\"lat\":35.9768231457432,\"lng\":128.944137387794,\"address\":\"야사동 충효로 120\",\"totalDealAmount\":114,\"maxDealAmount\":8500,\"minDealAmount\":4500,\"avgDealAmount\":5.692982456140351,\"avgArea\":53.3434210526315},{\"aptCode\":47230000000027,\"apartmentName\":\"부경웰빙타운\",\"lat\":35.9754303418083,\"lng\":128.94414368135,\"address\":\"야사동 새말1길 158-29\",\"totalDealAmount\":14,\"maxDealAmount\":24100,\"minDealAmount\":13300,\"avgDealAmount\":18.071428571428573,\"avgArea\":103.9372071428571},{\"aptCode\":47230000000037,\"apartmentName\":\"동신캐슬\",\"lat\":35.9770146599208,\"lng\":128.942349856214,\"address\":\"야사동 모란4길 154-3\",\"totalDealAmount\":9,\"maxDealAmount\":18000,\"minDealAmount\":14000,\"avgDealAmount\":15.777777777777779,\"avgArea\":83.88459999999999}]}]}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/realty/realty-info")
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                        .andExpect(content().json(expect));
    }
}
