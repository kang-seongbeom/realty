package com.ssafy.realty.custom_deal.application.port.out.dto.wrap;

import com.ssafy.realty.custom_deal.application.port.out.CustomSummaryDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomSummaryDtos {
    List<CustomSummaryDto> data;

    public CustomSummaryDtos(List<CustomSummaryDto> data) {
        this.data = data;
    }
}
