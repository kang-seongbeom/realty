package com.ssafy.realty.realty.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomInfo {
    private CustomInfoId customInfoId;
    private CustomInfoData customInfoData;

    @Data
    public static class CustomInfoId{
        Long id;
    }

    @Data
    public static class CustomInfoData{
        String title;
        String author;
        LocalDateTime date;
        Integer view;
        Integer like;
    }
}
