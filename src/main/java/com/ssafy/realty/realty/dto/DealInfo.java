package com.ssafy.realty.realty.dto;

import lombok.Data;

@Data
public class DealInfo implements Comparable<DealInfo>{
    private DealInfoArtCode dealInfoArtCode;
    private DealInfoData dealInfoData;

    @Data
    public static class DealInfoArtCode{
        private Long artCode;
    }

    @Data
    public static class DealInfoData{
        private String apartmentName;
        private Double lat;
        private Double lng;
        private String address;
        private Integer floor;
        private Long dealAmount;
    }

    @Data
    public static class DealDay{
        private Integer dealYear;
        private Integer dealMonth;
        private Integer dealDay;
    }

    @Override
    public int compareTo(DealInfo o) {
        return Long.compare(o.getDealInfoData().getDealAmount(), this.getDealInfoData().getDealAmount());
    }
}
