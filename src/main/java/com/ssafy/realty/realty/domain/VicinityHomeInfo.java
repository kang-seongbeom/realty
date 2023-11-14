package com.ssafy.realty.realty.domain;

import lombok.*;

@Value
public class VicinityHomeInfo {
    VicinityHomeInfoId vicinityHomeInfoId;
    VicinityHomeInfoData vicinityHomeInfoData;


    public static VicinityHomeInfo init(Long aptCode,
                                        String apartmentName,
                                        Double lat, Double lng,
                                        String address, Integer totalDealAmount,
                                        Long maxDealAmount, Long minDealAmount,
                                        Double avgDealAmount, Double avgArea) {

        return new VicinityHomeInfo(
                new VicinityHomeInfoId(aptCode),
                new VicinityHomeInfoData(
                        apartmentName,
                        lat, lng,
                        address, totalDealAmount,
                        maxDealAmount, minDealAmount,
                        avgDealAmount, avgArea)
        );
    }

    @Value
    public static class VicinityHomeInfoId{
        Long aptCode;
    }

    @Value
    public static class VicinityHomeInfoData {
        String apartmentName;
        Double lat;
        Double lng;
        String address;
        Integer totalDealAmount;
        Long maxDealAmount;
        Long minDealAmount;
        Double avgDealAmount;
        Double avgArea;
    }
}
