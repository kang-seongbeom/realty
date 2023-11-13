package com.ssafy.realty.realty.application.port.out.dto.wrap;


import com.ssafy.realty.realty.application.port.out.dto.TotalHistoryDealInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class TotalHistoryDealInfos {

    List<TotalHistoryDealInfo> data;

    public TotalHistoryDealInfos(List<TotalHistoryDealInfo> data) {
        this.data = data;
    }
}
