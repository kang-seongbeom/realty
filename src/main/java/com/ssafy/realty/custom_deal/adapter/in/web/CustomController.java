package com.ssafy.realty.custom_deal.adapter.in.web;

import com.ssafy.realty.common.swagger.ApiResponsesCommon;
import com.ssafy.realty.custom_deal.adapter.in.web.mapper.WebCustomMapper;
import com.ssafy.realty.custom_deal.adapter.in.web.payload.SearchCustomPayload;
import com.ssafy.realty.custom_deal.application.port.in.CommandCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.in.QueryCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.in.dto.*;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/custom")
@Api(tags = {"Custom Controller V1"})
@RequiredArgsConstructor
class CustomController {

    private final QueryCustomUseCase queryCustomUseCase;
    private final CommandCustomUseCase commandCustomUseCase;

    private final WebCustomMapper webCustomMapper;

    @GetMapping("/my-catalog")
    @ApiOperation(value = "본인이 만든 모든 커스텀 정보", notes = "본인이 만든 커스텀 매물 정보 반환")
    @ApiResponsesCommon
    ResponseEntity<CustomSummaryDtos> myCustomInfos(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                    Pageable pageable) {
        OwnCustomCatalogDto ownCustomCatalogDto =
                webCustomMapper.mapToOwnCustomCatalogDto(principalDetails.getUser().getId(), pageable);
        CustomSummaryDtos summaryDtos = queryCustomUseCase.myCustomInfos(ownCustomCatalogDto);
        return ResponseEntity.ok(summaryDtos);
    }

    @GetMapping("/is-owner/{customId}")
    @ApiOperation(value = "커스텀 작성자인지 확인", notes = "커스텀 작성자 유무 반환")
    @ApiResponsesCommon
    ResponseEntity<Boolean> isOwner(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @PathVariable Long customId) {
        IsOwnerDto isOwnerDto = webCustomMapper.mapToIsOwnerDto(principalDetails.getUser().getId(), customId);
        boolean isOwner = queryCustomUseCase.isOwner(isOwnerDto);
        return ResponseEntity.ok(isOwner);
    }

    @GetMapping("/search")
    @ApiOperation(value = "커스텀 검색", notes = "검색 후 조건에 맞는 커스텀 반환")
    @ApiResponsesCommon
    ResponseEntity<CustomSummaryDtos> search(@ModelAttribute SearchCustomPayload searchCustomPayload, Pageable pageable) {
        SearchCustomDto searchCustomDto = webCustomMapper.mapToSearchCustomDto(searchCustomPayload, pageable);
        CustomSummaryDtos summaryDtos =  queryCustomUseCase.search(searchCustomDto);

        return ResponseEntity.ok(summaryDtos);
    }

    @PostMapping("/star/{customId}")
    @ApiOperation(value = "좋아요", notes = "좋아요(star)로 사용자와 매물 정보 글 연결")
    @ApiResponsesCommon
    ResponseEntity<Void> starCustom(@AuthenticationPrincipal PrincipalDetails principalDetails,
                              @PathVariable Long customId){
        StarCustomDto starCustomDto = webCustomMapper.mapToStarIncreaseDto(principalDetails.getUser().getId(), customId);
        commandCustomUseCase.starIncrease(starCustomDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/star/own")
    @ApiOperation(value = "좋아요", notes = "사용자의 좋아요 글 반환")
    @ApiResponsesCommon
    ResponseEntity<CustomSummaryDtos> onwStarCustom(@AuthenticationPrincipal PrincipalDetails principalDetails, Pageable pageable){
        OwnStarCustomDto ownStarCustomDto = webCustomMapper.mapToOwnStarDto(principalDetails.getUser().getId(), pageable);
        CustomSummaryDtos customSummaryDtos = queryCustomUseCase.ownStarCustom(ownStarCustomDto);
        return ResponseEntity.ok(customSummaryDtos);
    }
}
