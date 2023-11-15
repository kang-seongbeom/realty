package com.ssafy.realty.realty.adapter.in.web;


import com.ssafy.realty.common.swagger.ApiResponsesCommon;
import com.ssafy.realty.realty.adapter.in.web.mapper.WebControllerMapper;
import com.ssafy.realty.realty.adapter.in.web.payload.MarkerPayload;
import com.ssafy.realty.realty.adapter.in.web.payload.SavePayload;
import com.ssafy.realty.realty.adapter.in.web.payload.wrap.MarkerPayloads;
import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import com.ssafy.realty.realty.application.port.in.CommandRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.QueryRealtyUseCase;
import com.ssafy.realty.realty.application.port.common_dto.MarkerDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveTemporaryDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.TotalHistoryDealInfoDtos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.domain.wrap.Markers;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/realty")
@Api(tags = {"Realty Controller V1"})
class RealtyController {

    private final QueryRealtyUseCase queryRealtyUseCase;
    private final CommandRealtyUseCase commandRealtyUseCase;

    private final WebControllerMapper webControllerMapper;

    private boolean isExistTemporary(Long userId){
        return queryRealtyUseCase.isTemporarySaved(userId);
    }

    @GetMapping("/tmp/is-saved")
    @ApiOperation(value = "임시 저장된 마커 존재 확인", notes = "임시 저장된 마커 확인")
    @ApiResponsesCommon
    ResponseEntity<Void> isSavedTmpMarkers(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (isExistTemporary(principalDetails.getUser().getId())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tmp")
    @ApiOperation(value = "임시 저장된 마커 호출", notes = "임시 저장된 마커를 호출해 반환")
    @ApiResponsesCommon
    ResponseEntity<MarkerDtos> loadTmpMarkers(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();

        if(!isExistTemporary(userId)){
            throw new NoResultException("임시 저장된 데이터가 없습니다.");
        }

        MarkerDtos markerDtos = queryRealtyUseCase.loadTemporary(userId);

        return ResponseEntity.ok(markerDtos);
    }

    @PostMapping("/tmp/save")
    @ApiOperation(value = "마커들 임시 저장", notes = "마커들 임시 저장")
    @ApiResponsesCommon
    ResponseEntity<Void> saveTmpMarkers(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @RequestBody MarkerPayloads payloads) {

        SaveTemporaryDto saveTemporaryDto = webControllerMapper.mapToSaveTemporaryDto(
                principalDetails.getUser().getId(),
                payloads);
        commandRealtyUseCase.saveTemporary(saveTemporaryDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/realty-info")
    @ApiOperation(value = "마커 주변 집 정보", notes = "마커의 정보를 확인해 필터링 한 뒤, 매물 정보 반환")
    @ApiResponsesCommon
    ResponseEntity<VicinityHomeInfoDtos> markerVicinityHome(@RequestBody MarkerPayload payload) {
        MarkerDto markerDto = webControllerMapper.mapToMakerDto(payload);
        VicinityHomeInfoDtos dto = queryRealtyUseCase.queryMarkerVicinityHome(markerDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{aptCode}")
    @ApiOperation(value = "집의 전체 거래 정보", notes = "하나의 집에 대한 전체 거래 정보를 조회 후 반환한다.")
    @ApiResponsesCommon
    ResponseEntity<TotalHistoryDealInfoDtos> totalHistory(@PathVariable String aptCode) {
        TotalHistoryDealInfoDtos totalHistoryDealInfoDtos = queryRealtyUseCase.queryTotalHistory(aptCode);
        return ResponseEntity.ok(totalHistoryDealInfoDtos);
    }

    @PostMapping("/save")
    @ApiOperation(value = "매물 정보 저장", notes = "사용자가 만든 매물 정보를 저장한다.")
    @ApiResponsesCommon
    ResponseEntity<Void> save(@AuthenticationPrincipal PrincipalDetails principalDetails,
                              @Valid @RequestBody SavePayload savePayload) {
        SaveDto saveDto = webControllerMapper.mapToSaveDto(principalDetails.getUser().getId(), savePayload);
        commandRealtyUseCase.save(saveDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/custom/{customId}")
    @ApiOperation(value = "커스텀 매물 정보 보기", notes = "다른(or 같은) 사용자가 만든 상세 매물 정보 확인")
    @ApiResponsesCommon
    ResponseEntity<MarkerDtos> detailCustomInfo(@PathVariable Long customId) {
        MarkerDtos markerDtos = queryRealtyUseCase.queryCustomInfo(customId);
        return ResponseEntity.ok(markerDtos);
    }
}
