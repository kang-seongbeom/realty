package com.ssafy.realty.realty.application.port.out.dto;

import com.ssafy.realty.common.TransportationType;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VicinityHomeInfosDto {

    private TransportationType type;
    private Integer time;
    private List<homeSummaryInfo> homeSummaryInfos;

    @Builder
    public static class homeSummaryInfo{
        private Long aptCode;
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
