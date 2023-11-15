package com.ssafy.realty.custom_deal.application.port.in;

import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;

public interface QueryCustomUseCase {

    CustomSummaryDtos total();

    CustomSummaryDtos myCustomInfos(Long id);
}
