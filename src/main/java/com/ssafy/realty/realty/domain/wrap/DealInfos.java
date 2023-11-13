package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.DealInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class DealInfos {
    private final List<DealInfo> dealInfos;

    public DealInfos(List<DealInfo> dealInfos) {
        this.dealInfos = dealInfos;
    }
}
