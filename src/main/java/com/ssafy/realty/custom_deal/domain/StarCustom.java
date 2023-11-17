package com.ssafy.realty.custom_deal.domain;

import lombok.Value;

@Value
public class StarCustom {
    StarCustomUserId starCustomUserId;
    StarCustomId starCustomId;

    public static StarCustom init(Long userId, Long customId){
        return new StarCustom(new StarCustomUserId(userId), new StarCustomId(customId));
    }

    @Value
    public static class StarCustomUserId {
        Long value;
    }

    @Value
    public static class StarCustomId {
        Long value;
    }
}
