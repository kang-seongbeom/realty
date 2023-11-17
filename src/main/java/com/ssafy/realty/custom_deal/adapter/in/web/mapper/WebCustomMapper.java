package com.ssafy.realty.custom_deal.adapter.in.web.mapper;

import com.ssafy.realty.common.SearchType;
import com.ssafy.realty.custom_deal.adapter.in.web.payload.SearchCustomPayload;
import com.ssafy.realty.custom_deal.application.port.in.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class WebCustomMapper {

    public CustomCatalogDto mapToCustomCatalog(Pageable pageable){
        return new CustomCatalogDto(pageable);
    }

    public OwnCustomCatalogDto mapToOwnCustomCatalogDto(Long userId, Pageable pageable){
        return new OwnCustomCatalogDto(userId, pageable);
    }

    public IsOwnerDto mapToIsOwnerDto(Long userId, Long customId){
        return new IsOwnerDto(userId, customId);
    }

    public SearchCustomDto mapToSearchCustomDto(SearchCustomPayload searchCustomPayload, Pageable pageable){
        return new SearchCustomDto(
                SearchType.findBySearchValue(searchCustomPayload.getType()),
                searchCustomPayload.getValue(),
                pageable
        );
    }

    public ViewIncreaseDto mapToViewIncreaseDto(Long customId) {
        return new ViewIncreaseDto(customId);
    }
}
