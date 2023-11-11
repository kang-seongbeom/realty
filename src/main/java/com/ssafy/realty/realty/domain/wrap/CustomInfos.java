package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.CustomInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomInfos {
    List<CustomInfo> customInfos;

    public CustomInfos(List<CustomInfo> customInfos) {
        this.customInfos = customInfos;
    }
}
