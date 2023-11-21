package com.ssafy.realty.custom_deal.application.port.out;

import com.ssafy.realty.custom_deal.domain.*;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;

public interface QueryCustomPort {

    Summaries myCustomInfos(OwnCustomCatalog ownCustomCatalog);

    boolean isOwner(IsOwner isOwner);

    Summaries search(Search search);

    Summaries ownStarCustom(OwnStarCustom ownStarCustom);
}
