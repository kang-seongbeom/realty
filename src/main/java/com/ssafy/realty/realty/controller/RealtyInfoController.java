package com.ssafy.realty.realty.controller;

import com.ssafy.realty.common.swagger.ApiResponsesCommon;
import com.ssafy.realty.realty.dto.Marker;
import com.ssafy.realty.realty.dto.wrap.Homes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/realty-info")
@Api(tags = {"Realty Info Controller V1"})
public class RealtyInfoController {

    @PostMapping
    @ApiOperation(value = "마커 주변 집 정보", notes = "마커의 정보를 확인해 필터링 한 뒤, 매물 정보 반환")
    @ApiResponsesCommon
    ResponseEntity<Homes> markerVicinityHomeInfo(@RequestBody Marker marker){
        Homes homes = new Homes(Collections.emptyList());
        return ResponseEntity.ok(homes);
    }
}
