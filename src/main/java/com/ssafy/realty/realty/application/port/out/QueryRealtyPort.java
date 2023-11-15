package com.ssafy.realty.realty.application.port.out;

import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.Markers;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;

public interface QueryRealtyPort {
    TotalVicinityHomeInfos queryMarkerVicinityHome(Marker marker);

    DealInfos queryTotalHistory(String aptCode);

    Markers queryCustomInfo(Long customId);

    boolean isTemporarySaved(Long userId);

    Markers loadTemporary(Long userId);
}
