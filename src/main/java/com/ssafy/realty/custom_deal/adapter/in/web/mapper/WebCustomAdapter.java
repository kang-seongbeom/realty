package com.ssafy.realty.custom_deal.adapter.in.web.mapper;

import com.ssafy.realty.custom_deal.application.port.in.dto.IsOwnerDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.CustomCatalogDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.OwnCustomCatalogDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class WebCustomAdapter {

    public CustomCatalogDto mapToCustomCatalog(Pageable pageable){
        return new CustomCatalogDto(pageable);
    }

    public OwnCustomCatalogDto mapToOwnCustomCatalogDto(Long userId, Pageable pageable){
        return new OwnCustomCatalogDto(userId, pageable);
    }

    public IsOwnerDto mapToIsOwnerDto(Long userId, Long customId){
        return new IsOwnerDto(userId, customId);
    }
}
