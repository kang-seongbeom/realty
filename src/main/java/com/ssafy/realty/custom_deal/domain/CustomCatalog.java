package com.ssafy.realty.custom_deal.domain;

import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
public class CustomCatalog {
    
    CustomCatalogData customCatalogData;
    
    public static CustomCatalog init(Pageable pageable){
        return new CustomCatalog(new CustomCatalogData(pageable));
    }
    
    @Value
    public static class CustomCatalogData {
        Pageable pageable;
    }
    
}
