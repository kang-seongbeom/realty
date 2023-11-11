package com.ssafy.realty.realty.application.port.out;

import com.ssafy.realty.realty.domain.DealInfo;
import com.ssafy.realty.realty.domain.Marker;

import java.util.List;

public interface QueryRealtyPort {
    List<DealInfo> queryMarkerVicinityHome(Marker marker);
}
