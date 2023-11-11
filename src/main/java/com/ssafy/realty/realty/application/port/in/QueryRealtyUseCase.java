package com.ssafy.realty.realty.application.port.in;

import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;

public interface QueryRealtyUseCase {
    void queryMarkerVicinityHome(MarkerDto markerDto);
}
