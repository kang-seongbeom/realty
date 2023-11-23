package com.ssafy.realty.user.application.service.UserMailGenerator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class UserMailSubjectContentGenerator {

    private final String MAIL_FORMAT = "<p>임시 비밀번호 : %s</p> <br> <a href='%s'>이동하기</a>";
    private final String SUBJECT = "ㅂㄷㅅ";

    public String generateSubject(){
        return this.SUBJECT;
    }

    public String generateContent(String rawPassword){
        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(5173)
                .path("login")
                .build(true)
                .toUriString();
        return String.format(MAIL_FORMAT, rawPassword, uri);
    }
}
