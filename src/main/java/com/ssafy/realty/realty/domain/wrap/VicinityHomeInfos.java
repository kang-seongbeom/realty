package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.common.TransportationType;
import com.ssafy.realty.realty.domain.VicinityHomeInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class VicinityHomeInfos {
    List<VicinityHomeInfo> vicinityHomeInfos;
    TransportationType type;
    Integer time;

    public VicinityHomeInfos(List<VicinityHomeInfo> vicinityHomeInfos, TransportationType type, Integer time) {
        this.vicinityHomeInfos = vicinityHomeInfos;
        this.type = type;
        this.time = time;
    }
}
