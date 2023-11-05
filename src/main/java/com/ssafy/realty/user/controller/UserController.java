package com.ssafy.realty.user.controller;

import com.ssafy.realty.realty.controller.swagger.ApiResponsesCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @PostMapping("/regist")
    @ApiOperation(value = "회원 가입", notes = "회원가입")
    @ApiResponsesCommon
    ResponseEntity<Void> regist(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인")
    @ApiResponsesCommon
    ResponseEntity<Void> login(){
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

    @DeleteMapping("/user/delete/{id}")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정")
    @ApiResponsesCommon
    ResponseEntity<Void> delete(){
        return ResponseEntity.ok().build();
    }
}
