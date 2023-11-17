package com.ssafy.realty.custom_deal.domain;

import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
public class OwnStarCustom {

    OwnStarCustomUserId ownStarCustomUserId;
    OwnStarCustomData ownStarCustomData;

    public static OwnStarCustom init(Long userId, Pageable pageable){
        return new OwnStarCustom(new OwnStarCustomUserId(userId), new OwnStarCustomData(pageable));
    }

    @Value
    public static class OwnStarCustomUserId{
        Long value;
    }

    @Value
    public static class OwnStarCustomData{
        Pageable pageable;
    }
}
