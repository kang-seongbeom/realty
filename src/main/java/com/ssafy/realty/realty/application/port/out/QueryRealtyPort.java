package com.ssafy.realty.realty.application.port.out;

import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;

public interface QueryRealtyPort {
    TotalVicinityHomeInfos queryMarkerVicinityHome(Marker marker);

    DealInfos queryTotalHistory(String aptCode);
}
