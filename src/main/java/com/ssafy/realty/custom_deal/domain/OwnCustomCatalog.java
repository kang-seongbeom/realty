package com.ssafy.realty.custom_deal.domain;

import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
public class OwnCustomCatalog {

    OwnCustomCatalogUserId ownCustomCatalogUserId;
    OwnCustomCatalogData ownCustomCatalogData;

    public static OwnCustomCatalog init(Long userId, Pageable pageable){
        return new OwnCustomCatalog(
                new OwnCustomCatalogUserId(userId),
                new OwnCustomCatalogData(pageable)
        );
    }


    @Value
    public static class OwnCustomCatalogUserId{
        Long value;
    }

    @Value
    public static class OwnCustomCatalogData{
        Pageable pageable;
    }
}
