package com.ssafy.realty.realty.application.port.out.dto.wrap;


import com.ssafy.realty.realty.application.port.out.dto.TotalHistoryDealInfoDto;
import lombok.Getter;

import java.util.List;

@Getter
public class TotalHistoryDealInfoDtos {

    List<TotalHistoryDealInfoDto> data;

    public TotalHistoryDealInfoDtos(List<TotalHistoryDealInfoDto> data) {
        this.data = data;
    }
}
