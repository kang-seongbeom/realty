package com.ssafy.realty.custom_deal.adapter.in.web;

import com.ssafy.realty.common.Role;
import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomDealJpaRepository;
import com.ssafy.realty.realty.adapter.out.CommandRealtyPersistenceAdapter;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.Save;
import com.ssafy.realty.realty.domain.wrap.Markers;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import com.ssafy.realty.security.config.jwt.JwtManager;
import com.ssafy.realty.security.entity.User;
import com.ssafy.realty.security.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandRealtyPersistenceAdapter commandRealtyPersistenceAdapter;

    @Autowired
    private CustomDealJpaRepository customDealJpaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtManager jwtManager;

    @Test
    @Transactional
    public void total() throws Exception {
        // given
        Long userId = defaultUser().getId();
        saveCustom(userId);

        List<CustomDealJpaEntity> all = customDealJpaRepository.findAll();
        Long lastInsertId = all.get(all.size() - 1).getId();
        String now = now().format(DateTimeFormatter.ISO_DATE);
        String expect = String.format("{\"data\":[{\"id\":%d,\"author\":\"ksb\",\"title\":\"kkk 제목 title\",\"look\":0,\"start\":0,\"createDate\":\"%s\"}]}", lastInsertId, now);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/custom/total");

        // when, then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expect));
    }

    @Test
    @Transactional
    public void myCustomInfos() throws Exception {
        Long defaultUserId = defaultUser().getId();
        User user = user("dkssud@gmail.com");
        Long userId = user.getId();

        String accessToken = getAuthorizedUserToken(user);

        saveCustom(defaultUserId);
        saveCustom(userId);
        saveCustom(userId);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/custom/my-custom")
                .header("accessToken", accessToken);

        // when
        String responseBody = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseBody);
        int length = jsonObject.getJSONArray("data").length();

        // then
        assertThat(length).isEqualTo(2);
    }

    private User defaultUser() {
        User user = new User(null, "custome@gmail.com", encoder.encode("a1234567"), "ksb", Role.USER);
        return userRepository.save(user);
    }

    private User user(String username){
        User user = new User(null, username, encoder.encode("a1234567"), "ksb", Role.USER);
        return userRepository.save(user);
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

    private static Markers customData() {
        List<String[]> ts = new ArrayList<>();
        ts.add(new String[]{"walk", "5"});
        ts.add(new String[]{"bicycle", "10"});

        Marker marker1 = Marker.init(12.0, 133.0,
                "address", "2017-03-23",
                "2020-04-04",
                10L, 20L,
                10.1, 11.1, ts);
        Marker marker2 = Marker.init(22.0, 233.0,
                "address", "2010-01-01",
                "2050-12-31",
                100L, 20123L,
                11230.1, 111233.1, ts);
        List<Marker> data = new ArrayList<>();
        data.add(marker1);
        data.add(marker2);

        return new Markers(data);
    }

    private void saveCustom(Long userId){
        String title = "kkk 제목 title";

        commandRealtyPersistenceAdapter.save(Save.init(userId, title, customData()));
    }
}