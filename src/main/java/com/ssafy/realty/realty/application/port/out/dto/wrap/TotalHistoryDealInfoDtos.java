package com.ssafy.realty.realty.application.port.out.dto.wrap;


import com.ssafy.realty.realty.application.port.out.dto.TotalHistoryDealInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TotalHistoryDealInfoDtos {

    private String aptCode;
    private String apartmentName;
    private Double lat;
    private Double lng;
    private String address;
    private List<TotalHistoryDealInfoDto> data;
}
