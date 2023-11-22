package com.ssafy.realty.realty.domain;

import lombok.Value;

@Value
public class ViewIncrease {
    ViewIncreaseCustomId viewIncreaseCustomId;

    public static ViewIncrease init(Long customId){
        return new ViewIncrease(new ViewIncreaseCustomId(customId));
    }

    @Value
    public static class ViewIncreaseCustomId{
        Long value;
    }
}
