package com.ssafy.realty.realty.adapter.in.web;


import com.ssafy.realty.common.swagger.ApiResponsesCommon;
import com.ssafy.realty.realty.adapter.in.web.mapper.WebControllerMapper;
import com.ssafy.realty.realty.adapter.in.web.payload.MarkerPayload;
import com.ssafy.realty.realty.adapter.in.web.payload.SavePayload;
import com.ssafy.realty.realty.application.port.in.CommandRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.QueryRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.TotalHistoryDealInfos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.domain.wrap.Markers;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/realty")
@Api(tags = {"Realty Controller V1"})
class RealtyController {

    private final WebControllerMapper webControllerMapper;

    private final QueryRealtyUseCase queryRealtyUseCase;
    private final CommandRealtyUseCase commandRealtyUseCase;

    @GetMapping("/is-saved")
    @ApiOperation(value = "임시 저장된 마커 존재 확인", notes = "임시 저장된 마커 확인")
    @ApiResponsesCommon
    ResponseEntity<Void> isSavedTmpMarkers(){
        boolean isSaved = true;
        if(isSaved){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ApiOperation(value = "임시 저장된 마커 호출", notes = "임시 저장된 마커를 호출해 반환")
    @ApiResponsesCommon
    ResponseEntity<Markers> loadTmpMarkers(){
        Markers markers = new Markers(Collections.emptyList());
        return ResponseEntity.ok(markers);
    }

    @PostMapping("/tm-save")
    @ApiOperation(value = "마커 임시 저장", notes = "마커 임시 저장")
    @ApiResponsesCommon
    ResponseEntity<Void> saveTmpMarkers(@RequestBody Markers markers){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/realty-info")
    @ApiOperation(value = "마커 주변 집 정보", notes = "마커의 정보를 확인해 필터링 한 뒤, 매물 정보 반환")
    @ApiResponsesCommon
    ResponseEntity<VicinityHomeInfoDtos> markerVicinityHome(@RequestBody MarkerPayload payload){
        MarkerDto markerDto = webControllerMapper.mapToMakerDto(payload);
        VicinityHomeInfoDtos dto = queryRealtyUseCase.queryMarkerVicinityHome(markerDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{aptCode}")
    @ApiOperation(value = "집의 전체 거래 정보", notes = "하나의 집에 대한 전체 거래 정보를 조회 후 반환한다.")
    @ApiResponsesCommon
    ResponseEntity<TotalHistoryDealInfos> totalHistory(@PathVariable String aptCode){
        TotalHistoryDealInfos totalHistoryDealInfos = queryRealtyUseCase.queryTotalHistory(aptCode);
        return ResponseEntity.ok(totalHistoryDealInfos);
    }

    @PostMapping("/save")
    @ApiOperation(value = "매물 정보 저장", notes = "사용자가 만든 매물 정보를 저장한다.")
    @ApiResponsesCommon
    ResponseEntity<Void> save(@AuthenticationPrincipal PrincipalDetails principalDetails,
                              @Valid @RequestBody SavePayload savePayload){
        SaveDto saveDto = webControllerMapper.mapToSaveDto(principalDetails.getUser().getId(), savePayload);
        commandRealtyUseCase.save(saveDto);
        return ResponseEntity.ok().build();
    }
}
