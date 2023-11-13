package com.ssafy.realty.realty.application.port.in;

import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;

public interface QueryRealtyUseCase {
    VicinityHomeInfoDtos queryMarkerVicinityHome(MarkerDto markerDto);
}
