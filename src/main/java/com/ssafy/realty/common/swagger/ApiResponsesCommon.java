package com.ssafy.realty.common.swagger;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(code = 200, message = "조회 성공"),
        @ApiResponse(code = 204, message = "데이터가 존재하지 않습니다."),
        @ApiResponse(code = 404, message = "페이지가 존재하지 않습니다."),
        @ApiResponse(code = 500, message = "서버 에러")
})
public @interface ApiResponsesCommon {
}
