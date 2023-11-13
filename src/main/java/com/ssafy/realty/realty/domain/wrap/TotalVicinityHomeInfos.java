package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.Marker;
import lombok.Getter;

import java.util.List;

@Getter
public class TotalVicinityHomeInfos {
    private final List<VicinityHomeInfos> total;

    public TotalVicinityHomeInfos(List<VicinityHomeInfos> total) {
        this.total = total;
    }
}
