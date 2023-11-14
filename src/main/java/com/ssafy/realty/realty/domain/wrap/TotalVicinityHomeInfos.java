package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.Marker;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class TotalVicinityHomeInfos {
    List<VicinityHomeInfos> total;
}
