package com.ssafy.realty.realty.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VicinityHomeInfo {
    private VicinityHomeInfoId vicinityHomeInfoId;
    private VicinityHomeInfoData vicinityHomeInfoData;


    public static VicinityHomeInfo init(Long aptCode,
                                        String apartmentName,
                                        Double lat,
                                        Double lng,
                                        String address,
                                        Integer totalDealAmount,
                                        Long maxDealAmount,
                                        Long minDealAmount,
                                        Double avgDealAmount,
                                        Double avgArea) {

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

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class VicinityHomeInfoId{
        private Long aptCode;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class VicinityHomeInfoData {
        private String apartmentName;
        private Double lat;
        private Double lng;
        private String address;
        private Integer totalDealAmount;
        private Long maxDealAmount;
        private Long minDealAmount;
        private Double avgDealAmount;
        private Double avgArea;
    }
}
