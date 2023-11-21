package com.ssafy.realty.custom_deal.application.port.in;

import com.ssafy.realty.custom_deal.application.port.in.dto.*;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;

public interface QueryCustomUseCase {

    CustomSummaryDtos myCustomInfos(OwnCustomCatalogDto ownCustomCatalogDto);

    boolean isOwner(IsOwnerDto isOwnerDto);

    CustomSummaryDtos search(SearchCustomDto searchCustomDto);

    CustomSummaryDtos ownStarCustom(OwnStarCustomDto ownStarCustomDto);
}
