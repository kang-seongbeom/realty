package com.ssafy.realty.custom_deal.application.port.out;

import com.ssafy.realty.custom_deal.domain.CustomCatalog;
import com.ssafy.realty.custom_deal.domain.IsOwner;
import com.ssafy.realty.custom_deal.domain.OwnCustomCatalog;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;

public interface QueryCustomPort {

    Summaries catalogs(CustomCatalog customCatalog);

    Summaries myCustomInfos(OwnCustomCatalog ownCustomCatalog);

    boolean isOwner(IsOwner isOwner);
}
