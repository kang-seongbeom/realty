package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.common.TransportationType;
import com.ssafy.realty.realty.domain.VicinityHomeInfo;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class VicinityHomeInfos {
    List<VicinityHomeInfo> vicinityHomeInfos;
    TransportationType type;
    Integer time;
}
