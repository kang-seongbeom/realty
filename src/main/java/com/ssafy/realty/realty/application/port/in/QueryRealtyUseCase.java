package com.ssafy.realty.realty.application.port.in;

import com.ssafy.realty.realty.application.port.common_dto.MarkerDto;
import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.TotalHistoryDealInfoDtos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;

public interface QueryRealtyUseCase {
    VicinityHomeInfoDtos queryMarkerVicinityHome(MarkerDto markerDto);

    TotalHistoryDealInfoDtos queryTotalHistory(String aptCode);

    MarkerDtos queryCustomInfo(Long customId);

    boolean isTemporarySaved(Long userId);
}
