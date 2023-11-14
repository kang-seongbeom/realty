package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.CustomInfo;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class CustomInfos {
    List<CustomInfo> customInfos;
}
