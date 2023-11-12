package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.Marker;
import lombok.Getter;

import java.util.List;

@Getter
public class TotalVicinityHomeInfos {
    private final List<VicinityHomeInfos> total;
    private final Marker marker;

    public TotalVicinityHomeInfos(List<VicinityHomeInfos> total, Marker marker) {
        this.total = total;
        this.marker = marker;
    }
}
