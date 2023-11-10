package com.ssafy.realty.realty.controller;

import com.ssafy.realty.realty.controller.swagger.ApiResponsesCommon;
import com.ssafy.realty.realty.dto.DealInfo;
import com.ssafy.realty.realty.dto.wrap.DealInfos;
import com.ssafy.realty.realty.dto.Marker;
import com.ssafy.realty.realty.dto.wrap.Markers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/realty")
@Api(tags = {"Realty Controller V1"})
class RealtyController {

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
    ResponseEntity<DealInfos> markerVicinityHomeInfo(@RequestBody Marker marker){
        DealInfos dealInfos = new DealInfos(Collections.emptyList());
        return ResponseEntity.ok(dealInfos);
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
