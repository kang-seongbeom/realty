package com.ssafy.realty.user.adapter.in.web.payload;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RegistPayloadTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("username이 email 형식일 때")
    public void isEmailForm() {
        // given
        RegistPayload registPayload = new RegistPayload("qkfka9045@gmail.com", "a1234567", "nick");

        // when
        Set<ConstraintViolation<RegistPayload>> violations = validator.validate(registPayload);

        // then
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("username이 email 형식이 아닐 때")
    public void notEmailForm() {
        // given
        RegistPayload registPayload = new RegistPayload("qkfka", "a1234567", "nick");

        // when
        Set<ConstraintViolation<RegistPayload>> violations = validator.validate(registPayload);

        // then
        assertThat(violations.size()).isNotEqualTo(0);
    }

    @Test
    @DisplayName("전부 null이 아닐 때")
    public void notNull() {
        // given
        RegistPayload registPayload1 = new RegistPayload("qkfka9045@gmail.com", "a1234567", "nick");

        // when
        Set<ConstraintViolation<RegistPayload>> violations = validator.validate(registPayload1);

        // then
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("하나라도 null일 때")
    public void isNull() {
        // given
        RegistPayload registPayload1 = new RegistPayload(null, "a1234567", "nick");
        RegistPayload registPayload2 = new RegistPayload("qkfka@gmail.com", null, "nick");
        RegistPayload registPayload3 = new RegistPayload("qkfka@gmail.com", "a1234567", null);

        // when
        Set<ConstraintViolation<RegistPayload>> violations1 = validator.validate(registPayload1);
        Set<ConstraintViolation<RegistPayload>> violations2 = validator.validate(registPayload2);
        Set<ConstraintViolation<RegistPayload>> violations3 = validator.validate(registPayload3);

        // then
        assertThat(violations1.size()).isNotEqualTo(0);
        assertThat(violations2.size()).isNotEqualTo(0);
        assertThat(violations3.size()).isNotEqualTo(0);
    }

    @Test
    @DisplayName("비밀번호 패턴 일치")
    public void matchedPassword() {
        // given
        RegistPayload registPayload = new RegistPayload("qkfka9045@gmail.com", "a1234567", "nick");

        // when
        Set<ConstraintViolation<RegistPayload>> violations = validator.validate(registPayload);

        //then
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("비밀번호 패턴 불일치")
    public void notMatchedPassword() {
        // given
        RegistPayload registPayload = new RegistPayload("qkfka9045@gmail.com", "567", "nick");

        // when
        Set<ConstraintViolation<RegistPayload>> violations = validator.validate(registPayload);

        //then
        assertThat(violations.size()).isNotEqualTo(0);
    }

    @Test
    @DisplayName("닉네임의 길이 미충족")
    public void satisfiedNicknameLength() {
        RegistPayload registPayload = new RegistPayload("qkfka9045@gmail.com", "a1234567", "n");

        // when
        Set<ConstraintViolation<RegistPayload>> violations = validator.validate(registPayload);

        //then
        assertThat(violations.size()).isNotEqualTo(0);
    }

}
