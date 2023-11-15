package com.ssafy.realty.custom_deal.adapter.in.web.mapper;

import com.ssafy.realty.custom_deal.application.port.in.IsOwnerDto;
import org.springframework.stereotype.Component;

@Component
public class WebCustomAdapter {

    public IsOwnerDto isOwnerDto(Long userId, Long customId){
        return new IsOwnerDto(userId, customId);
    }
}
