package com.ssafy.realty.custom_deal.adapter.in.web;

import com.ssafy.realty.common.swagger.ApiResponsesCommon;
import com.ssafy.realty.custom_deal.adapter.in.web.mapper.WebCustomAdapter;
import com.ssafy.realty.custom_deal.application.port.in.dto.IsOwnerDto;
import com.ssafy.realty.custom_deal.application.port.in.QueryCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.in.dto.CustomCatalogDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.OwnCustomCatalogDto;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/custom")
@Api(tags = {"Custom Controller V1"})
@RequiredArgsConstructor
class CustomController {

    private final QueryCustomUseCase queryCustomUseCase;

    private final WebCustomAdapter webCustomAdapter;

    @GetMapping("/catalog")
    @ApiOperation(value = "모든 커스텀 정보", notes = "모든 사용자들이 만든 커스텀 매물 정보 반환")
    @ApiResponsesCommon
    ResponseEntity<CustomSummaryDtos> totalCustomInfo(Pageable pageable) {
        CustomCatalogDto customCatalogDto = webCustomAdapter.mapToCustomCatalog(pageable);
        CustomSummaryDtos total = queryCustomUseCase.total(customCatalogDto);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/my-catalog")
    @ApiOperation(value = "본인이 만든 모든 커스텀 정보", notes = "본인이 만든 커스텀 매물 정보 반환")
    @ApiResponsesCommon
    ResponseEntity<CustomSummaryDtos> myCustomInfos(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                    Pageable pageable) {
        OwnCustomCatalogDto ownCustomCatalogDto =
                webCustomAdapter.mapToOwnCustomCatalogDto(principalDetails.getUser().getId(), pageable);
        CustomSummaryDtos info = queryCustomUseCase.myCustomInfos(ownCustomCatalogDto);
        return ResponseEntity.ok(info);
    }

    @GetMapping("/is-owner/{customId}")
    @ApiOperation(value = "커스텀 작성자인지 확인", notes = "커스텀 작성자 유무 반환")
    @ApiResponsesCommon
    ResponseEntity<Boolean> isOwner(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @PathVariable Long customId) {
        IsOwnerDto isOwnerDto = webCustomAdapter.mapToIsOwnerDto(principalDetails.getUser().getId(), customId);
        boolean isOwner = queryCustomUseCase.isOwner(isOwnerDto);
        return ResponseEntity.ok(isOwner);
    }

}
