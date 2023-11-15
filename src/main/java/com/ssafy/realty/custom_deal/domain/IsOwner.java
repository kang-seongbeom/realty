package com.ssafy.realty.custom_deal.domain;

import lombok.Getter;
import lombok.Value;

@Value
public class IsOwner {

    IsOwnerUserId isOwnerUserId;
    IsOwnerCustomId isOwnerCustomId;

    public static IsOwner init(Long userId, Long customId){
        return new IsOwner(new IsOwnerUserId(userId), new IsOwnerCustomId(customId));
    }

    @Value
    public static class IsOwnerUserId{
        Long value;
    }

    @Value
    public static class IsOwnerCustomId{
        Long value;
    }
}
