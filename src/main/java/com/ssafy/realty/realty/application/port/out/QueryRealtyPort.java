package com.ssafy.realty.realty.application.port.out;

import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.DealInfos;

import java.util.List;

public interface QueryRealtyPort {
    DealInfos queryMarkerVicinityHome(Marker marker);
}
