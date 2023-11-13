package com.ssafy.realty.realty.adapter.in.web;


import com.ssafy.realty.common.swagger.ApiResponsesCommon;
import com.ssafy.realty.realty.adapter.in.web.mapper.MarkerWebMapper;
import com.ssafy.realty.realty.adapter.in.web.payload.MarkerPayload;
import com.ssafy.realty.realty.application.port.in.QueryRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.domain.DealInfo;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.Markers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/realty")
@Api(tags = {"Realty Controller V1"})
class RealtyController {

    private final MarkerWebMapper markerWebMapper;

    private final QueryRealtyUseCase queryRealtyUseCase;

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
        MarkerDto markerDto = markerWebMapper.mapToMakerDto(payload);
        VicinityHomeInfoDtos dto = queryRealtyUseCase.queryMarkerVicinityHome(markerDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "상세 매물 정보", notes = "id에 해당하는 매물 정보를 반환")
    @ApiResponsesCommon
    ResponseEntity<DealInfo> detailHomeInfo(@PathVariable Integer id){
        DealInfo dealInfo = new DealInfo();
        return ResponseEntity.ok(dealInfo);
    }

    @PostMapping("/save")
    @ApiOperation(value = "상세 매물 정보", notes = "id에 해당하는 매물 정보를 반환")
    @ApiResponsesCommon
    ResponseEntity<Void> save(@RequestBody String title, @RequestBody Markers markers){
        return ResponseEntity.ok().build();
    }
}
