package com.ssafy.realty.user.adapter.in.web;

import com.ssafy.realty.realty.controller.swagger.ApiResponsesCommon;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import com.ssafy.realty.user.adapter.in.web.payload.RegistPayload;
import com.ssafy.realty.user.application.port.in.DeleteUserUseCase;
import com.ssafy.realty.user.application.port.in.RegistUserUseCase;
import com.ssafy.realty.user.application.port.in.dto.RegistDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final RegistUserUseCase registUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping("/regist")
    @ApiOperation(value = "회원가입", notes = "회원가입")
    @ApiResponsesCommon
    ResponseEntity<Void> regist(@RequestBody RegistPayload registPayload){
        RegistDto dto = RegistDto
                .builder()
                .username(registPayload.getUsername())
                .password(registPayload.getPassword())
                .nickname(registPayload.getPassword())
                .build();
        registUserUseCase.regist(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    @ApiOperation(value = "로그아웃", notes = "로그아웃")
    @ApiResponsesCommon
    ResponseEntity<Void> logout(){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/update/{id}")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정")
    @ApiResponsesCommon
    ResponseEntity<Void> update(){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/delete")
    @ApiOperation(value = "회원 정보 삭제", notes = "회원 정보 삭제")
    @ApiResponsesCommon
    ResponseEntity<Void> delete(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Long id = principalDetails.getUser().getId();
        deleteUserUseCase.delete(id);

        return ResponseEntity.ok().build();
    }
}
