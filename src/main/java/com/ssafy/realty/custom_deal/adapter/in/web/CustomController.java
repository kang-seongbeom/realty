package com.ssafy.realty.custom_deal.adapter.in.web;

import com.ssafy.realty.common.swagger.ApiResponsesCommon;
import com.ssafy.realty.custom_deal.application.port.in.QueryCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;
import com.ssafy.realty.realty.domain.wrap.CustomInfos;
import com.ssafy.realty.realty.domain.wrap.Markers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/custom")
@Api(tags = {"Custom Controller V1"})
@RequiredArgsConstructor
public class CustomController {

    private final QueryCustomUseCase queryCustomUseCase;

    @GetMapping("/total")
    @ApiOperation(value = "모든 커스텀 정보", notes = "모든 사용자들이 만든 커스텀 매물 정보 반환")
    @ApiResponsesCommon
    ResponseEntity<CustomSummaryDtos> totalCustomInfo() {
        CustomSummaryDtos total = queryCustomUseCase.total();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{customId}")
    ResponseEntity<Markers> detailCustomInfo(@PathVariable Long customId) {
        Markers markers = new Markers(Collections.emptyList());
        return ResponseEntity.ok(markers);
    }
}