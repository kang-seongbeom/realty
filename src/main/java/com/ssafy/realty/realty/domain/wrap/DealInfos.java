package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.DealInfo;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class DealInfos {
    List<DealInfo> dealInfos;
}
