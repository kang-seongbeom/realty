package com.ssafy.realty.custom_deal.application.port.out.dto.wrap;

import com.ssafy.realty.custom_deal.application.port.out.dto.CustomSummaryDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomSummaryDtos {
    private List<CustomSummaryDto> data;
    private Integer totalPage;
    private Integer currentPage;

    public CustomSummaryDtos(List<CustomSummaryDto> data, Integer totalPage, Integer currentPage) {
        this.data = data;
        this.totalPage = totalPage;
        this.currentPage = currentPage+1;
    }
}
