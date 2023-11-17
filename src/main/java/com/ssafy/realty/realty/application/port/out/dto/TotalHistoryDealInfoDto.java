package com.ssafy.realty.realty.application.port.out.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TotalHistoryDealInfoDto {

    private Integer floor;
    private Long dealAmount;
    private LocalDate dealDate;
}
