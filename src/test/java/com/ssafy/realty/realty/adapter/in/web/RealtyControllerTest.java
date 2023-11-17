package com.ssafy.realty.realty.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.realty.common.Role;
import com.ssafy.realty.realty.adapter.out.CommandRealtyPersistenceAdapter;
import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.repository.CustomJpaRepository;
import com.ssafy.realty.realty.domain.Save;
import com.ssafy.realty.realty.domain.SaveTemporary;
import com.ssafy.realty.realty.domain.wrap.Markers;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import com.ssafy.realty.security.config.jwt.JwtManager;
import com.ssafy.realty.security.entity.User;
import com.ssafy.realty.security.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("부동산 컨트롤러 통합 테스트")
@Transactional
class RealtyControllerTest extends RealtyData {

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

    @Autowired
    private CommandRealtyPersistenceAdapter commandRealtyPersistenceAdapter;

    @Autowired
    private CustomJpaRepository customJpaRepository;

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

        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/realty/" + dongCode);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("저장")
    public void save() throws Exception {
        // given
        JSONObject requestBody = new JSONObject();

        JSONArray markers = new JSONArray();
        markers.put(realtyInfoJsonBody());

        String title = "my own info";
        requestBody.put("title", title);
        requestBody.put("markers", markers);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/realty/save")
                .header("accessToken", getAccessTokenWithResgistDefaultUser())
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("수정")
    public void update() throws Exception {
        // given
        User user = registDefaultUser();
        saveDefaultCustom(user.getId());
        List<CustomJpaEntity> all = customJpaRepository.findAll();
        Long lastInsertCustomId = all.get(all.size() - 1).getId();

        JSONObject requestBody = new JSONObject();
        JSONArray markers = new JSONArray();
        markers.put(realtyInfoJsonBodyVersion2());

        requestBody.put("title", "update Title");
        requestBody.put("markers", markers);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch("/api/v1/realty/custom/update/" + lastInsertCustomId)
                .header("accessToken", getAuthorizedUserToken(user))
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // when
        ResultActions result = mockMvc.perform(request);

        // then
        result.andExpect(status().isOk());
        CustomJpaEntity customJpaEntity = customJpaRepository.findById(lastInsertCustomId).get();
        assertThat(customJpaEntity.getTitle()).isEqualTo("update Title");
        assertThat(customJpaEntity.getMarkers().get(0).getLat()).isEqualTo(realtyInfoJsonBodyVersion2().getDouble("lat"));
        assertThat(customJpaEntity.getMarkers().get(0).getLng()).isEqualTo(realtyInfoJsonBodyVersion2().getDouble("lng"));
        assertThat(customJpaEntity.getMarkers().get(0).getAddress()).isEqualTo(realtyInfoJsonBodyVersion2().getString("address"));
    }

    @Test
    @DisplayName("삭제")
    public void delete() throws Exception {
        User user = registDefaultUser();
        saveDefaultCustom(user.getId());
        List<CustomJpaEntity> all = customJpaRepository.findAll();
        Long lastInsertCustomId = all.get(all.size() - 1).getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/api/v1/realty/custom/delete/" + lastInsertCustomId)
                .header("accessToken", getAuthorizedUserToken(user))
                .contentType(MediaType.APPLICATION_JSON);

        // when
        ResultActions result = mockMvc.perform(request);

        // then
        result.andExpect(status().isOk());
        Optional<CustomJpaEntity> deleted = customJpaRepository.findById(lastInsertCustomId);
        assertThat(deleted).isNotPresent();
    }


    @Test
    @DisplayName("임시 저장")
    public void saveDefaultTmp() throws Exception {
        // given
        JSONObject requestBody = new JSONObject();

        JSONArray markers = new JSONArray();
        markers.put(realtyInfoJsonBody());

        requestBody.put("markers", markers);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/realty/tmp/save")
                .header("accessToken", getAccessTokenWithResgistDefaultUser())
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("기존에 임시 저장된 데이터가 있으면 덮어씌움")
    public void coverTmpData() throws Exception {
        // given
        User user = registDefaultUser();
        saveDefaultTmp(user.getId());

        JSONObject requestBody = new JSONObject();
        JSONArray markers = new JSONArray();
        markers.put(realtyInfoJsonBodyVersion2());

        requestBody.put("markers", markers);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/realty/tmp/save")
                .header("accessToken", getAuthorizedUserToken(user))
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON);

        // when
        mockMvc.perform(request)
                .andExpect(status().isOk());

        // then
        CustomJpaEntity customJpaEntity = customJpaRepository.findByUserId(user.getId());
        assertNotNull(customJpaEntity);
        assertThat(customJpaEntity.getMarkers().get(0).getLat()).isEqualTo(realtyInfoJsonBodyVersion2().getDouble("lat"));
        assertThat(customJpaEntity.getMarkers().get(0).getLng()).isEqualTo(realtyInfoJsonBodyVersion2().getDouble("lng"));
        assertThat(customJpaEntity.getMarkers().get(0).getAddress()).isEqualTo(realtyInfoJsonBodyVersion2().getString("address"));
    }

    @Test
    @DisplayName("임시 저장된  데이터가 있는지 확인")
    public void isSaveTmp() throws Exception {
        // given
        User user = registDefaultUser();

        saveDefaultTmp(user.getId());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/realty/tmp/is-saved")
                .header("accessToken", getAuthorizedUserToken(user))
                .contentType(MediaType.APPLICATION_JSON);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("임시 저장된  데이터가 없으면 204 반환")
    public void isNotSaveTmp() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/realty/tmp/is-saved")
                .header("accessToken", getAccessTokenWithResgistDefaultUser())
                .contentType(MediaType.APPLICATION_JSON);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("임시 저장된 데이터 로드")
    public void loadTmp() throws Exception {
        // given
        String expect = "{\"data\":[{\"lat\":12.0,\"lng\":133.0,\"address\":\"address\",\"filter\":{\"date\":{\"lower\":\"2017-03-23\",\"upper\":\"2020-04-04\"},\"dealAmount\":{\"lower\":10,\"upper\":20},\"area\":{\"lower\":10.1,\"upper\":11.1},\"transportations\":[{\"type\":\"WALK\",\"time\":5},{\"type\":\"BYCYCLE\",\"time\":10}]}},{\"lat\":22.0,\"lng\":233.0,\"address\":\"address\",\"filter\":{\"date\":{\"lower\":\"2010-01-01\",\"upper\":\"2050-12-31\"},\"dealAmount\":{\"lower\":100,\"upper\":20123},\"area\":{\"lower\":11230.1,\"upper\":111233.1},\"transportations\":[{\"type\":\"WALK\",\"time\":5},{\"type\":\"BYCYCLE\",\"time\":10}]}}]}";

        User user = registDefaultUser();

        saveDefaultTmp(user.getId());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/realty/tmp")
                .header("accessToken", getAuthorizedUserToken(user))
                .contentType(MediaType.APPLICATION_JSON);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expect));
    }

    @Test
    @DisplayName("저장된 커스컴 매물 정보 확인")
    public void custom() throws Exception {
        // given
        String expect = "{\"data\":[{\"lat\":12.0,\"lng\":133.0,\"address\":\"address\",\"filter\":{\"date\":{\"lower\":\"2017-03-23\",\"upper\":\"2020-04-04\"},\"dealAmount\":{\"lower\":10,\"upper\":20},\"area\":{\"lower\":10.1,\"upper\":11.1},\"transportations\":[{\"type\":\"WALK\",\"time\":5},{\"type\":\"BYCYCLE\",\"time\":10}]}},{\"lat\":22.0,\"lng\":233.0,\"address\":\"address\",\"filter\":{\"date\":{\"lower\":\"2010-01-01\",\"upper\":\"2050-12-31\"},\"dealAmount\":{\"lower\":100,\"upper\":20123},\"area\":{\"lower\":11230.1,\"upper\":111233.1},\"transportations\":[{\"type\":\"WALK\",\"time\":5},{\"type\":\"BYCYCLE\",\"time\":10}]}}]}";

        saveDefaultCustom(getUserId(registDefaultUser()));

        List<CustomJpaEntity> all = customJpaRepository.findAll();
        Long lastInsertCustomId = all.get(all.size() - 1).getId();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/realty/custom/" + lastInsertCustomId);

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expect));
    }


    private User registDefaultUser() {
        User user = new User(null, "realtyqkfka9045@gmail.com", encoder.encode("a1234567"), "nick", Role.USER);
        return userRepository.save(user);
    }

    private Long getUserId(User savedUser) {
        return savedUser.getId();
    }

    private String getAccessTokenWithResgistDefaultUser() {
        return getAuthorizedUserToken(registDefaultUser());
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

    private void saveDefaultCustom(Long userId) {
        String title = "kkk 제목 title";

        commandRealtyPersistenceAdapter.save(Save.init(userId, title, customData()));
    }

    private void saveDefaultTmp(Long userId) {
        commandRealtyPersistenceAdapter.saveTemporary(SaveTemporary.init(userId, customData()));
    }
}
