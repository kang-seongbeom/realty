package com.ssafy.realty.custom_deal.application.port.in;

import com.ssafy.realty.custom_deal.application.port.in.dto.IsOwnerDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.CustomCatalogDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.OwnCustomCatalogDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.SearchCustomDto;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;

public interface QueryCustomUseCase {

    CustomSummaryDtos total(CustomCatalogDto customCatalogDto);

    CustomSummaryDtos myCustomInfos(OwnCustomCatalogDto ownCustomCatalogDto);

    boolean isOwner(IsOwnerDto isOwnerDto);

    CustomSummaryDtos search(SearchCustomDto searchCustomDto);
}
