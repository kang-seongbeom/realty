package com.ssafy.realty.custom_deal.application.service;

import com.ssafy.realty.custom_deal.application.port.in.dto.IsOwnerDto;
import com.ssafy.realty.custom_deal.application.port.in.QueryCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.in.dto.CustomCatalogDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.OwnCustomCatalogDto;
import com.ssafy.realty.custom_deal.application.port.out.QueryCustomPort;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;
import com.ssafy.realty.custom_deal.application.service.mapper.CustomServiceMapper;
import com.ssafy.realty.custom_deal.domain.CustomCatalog;
import com.ssafy.realty.custom_deal.domain.IsOwner;
import com.ssafy.realty.custom_deal.domain.OwnCustomCatalog;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryCustomService implements QueryCustomUseCase {

    private final QueryCustomPort queryCustomPort;

    private final CustomServiceMapper customServiceMapper;

    @Override
    public CustomSummaryDtos total(CustomCatalogDto customCatalogDto) {
        CustomCatalog customCatalog = customServiceMapper.mapToCustomCatalog(customCatalogDto);
        Summaries catalogs = queryCustomPort.catalogs(customCatalog);
        return customServiceMapper.mapToCustomSummaryDtos(catalogs);
    }

    @Override
    public CustomSummaryDtos myCustomInfos(OwnCustomCatalogDto ownCustomCatalogDto) {
        OwnCustomCatalog ownCustomCatalog = customServiceMapper.mapToOwnCustomCatalog(ownCustomCatalogDto);
        Summaries catalogs = queryCustomPort.myCustomInfos(ownCustomCatalog);

        return customServiceMapper.mapToCustomSummaryDtos(catalogs);
    }

    @Override
    public boolean isOwner(IsOwnerDto isOwnerDto) {
        IsOwner isOwner = customServiceMapper.mapToIsOwner(isOwnerDto);
        return queryCustomPort.isOwner(isOwner);
    }
}
