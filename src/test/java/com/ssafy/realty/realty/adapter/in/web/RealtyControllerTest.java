package com.ssafy.realty.realty.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.realty.common.Role;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import com.ssafy.realty.security.config.jwt.JwtManager;
import com.ssafy.realty.security.entity.User;
import com.ssafy.realty.security.repository.UserRepository;
import com.ssafy.realty.user.application.port.in.CommandUserUseCase;
import com.ssafy.realty.user.application.port.in.QueryUserUseCase;
import com.ssafy.realty.user.application.port.in.dto.QueryResponseDto;
import org.json.JSONArray;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("부동산 컨트롤러 통합 테스트")
class RealtyControllerTest extends RealtyJsonData {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

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

    @Test
    @DisplayName("한 집의 전체 거래 정보")
    public void historyAHouse() throws Exception {
        String dongCode = "11110000000003";
        String expect = "{\"data\":[{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":9,\"dealAmount\":442000000,\"dealDate\":\"2015-01-19\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":7,\"dealAmount\":425000000,\"dealDate\":\"2015-03-09\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":8,\"dealAmount\":600000000,\"dealDate\":\"2015-09-22\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":8,\"dealAmount\":600000000,\"dealDate\":\"2015-12-22\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":8,\"dealAmount\":450000000,\"dealDate\":\"2016-07-04\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":7,\"dealAmount\":340000000,\"dealDate\":\"2016-08-15\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":9,\"dealAmount\":675000000,\"dealDate\":\"2017-07-14\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":7,\"dealAmount\":515000000,\"dealDate\":\"2017-10-16\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":9,\"dealAmount\":675000000,\"dealDate\":\"2017-11-09\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":8,\"dealAmount\":518000000,\"dealDate\":\"2018-03-20\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":8,\"dealAmount\":395000000,\"dealDate\":\"2018-08-15\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":8,\"dealAmount\":720000000,\"dealDate\":\"2018-09-15\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":7,\"dealAmount\":420000000,\"dealDate\":\"2018-11-11\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":10,\"dealAmount\":535000000,\"dealDate\":\"2019-08-10\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":7,\"dealAmount\":597000000,\"dealDate\":\"2019-10-15\"},{\"aptCode\":\"11110000000003\",\"apartmentName\":\"롯데미도파광화문빌딩\",\"lat\":37.5713864751051,\"lng\":126.973698680555,\"address\":\"당주동 세종대로23길 145\",\"floor\":8,\"dealAmount\":502000000,\"dealDate\":\"2020-04-30\"}]}";

        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/realty/" + dongCode);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expect));

    }

    @Test
    @DisplayName("저장")
    public void save() throws Exception {
        // given
        JSONObject requestBody = new JSONObject();

        JSONArray markers = new JSONArray();
        markers.put(realtyInfoJsonBody());

        String title = "나만의 매물 정보";
        requestBody.put("title", title);
        requestBody.put("markers", markers);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/realty/save")
                .header("accessToken", getAccessToken())
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    private String getAccessToken(){
        User user = new User(null, "qkfka9045@gmail.com", encoder.encode("a1234567"), "nick", Role.USER);
        userRepository.save(user);
        return getAuthorizedUserToken(user);
    }

    private String getAuthorizedUserToken(User saved) {
        PrincipalDetails principalDetails = new PrincipalDetails(saved);

        String accessToken = jwtManager.createAccessToken(principalDetails);

        if (accessToken != null && jwtManager.isTokenValid(accessToken)) {
            Authentication authentication = jwtManager.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return "Bearer " + accessToken;
    }
}
