package com.ssafy.realty.realty.domain;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CustomInfo {
    CustomInfoId customInfoId;
    CustomInfoData customInfoData;

    @Value
    public static class CustomInfoId{
        Long id;
    }

    @Value
    public static class CustomInfoData{
        String title;
        String author;
        LocalDateTime date;
        Integer view;
        Integer like;
    }
}
