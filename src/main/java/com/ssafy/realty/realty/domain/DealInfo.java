package com.ssafy.realty.realty.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DealInfo implements Comparable<DealInfo> {
    private DealInfoArtCode dealInfoArtCode;
    private DealInfoData dealInfoData;

    public static DealInfo init(String aptCode, String apartmentName, Double lat, Double lng,
                         String dong, String roadName, String jibun,
                         Integer floor, String dealAmount,
                         Integer year, Integer month, Integer day) {
        return new DealInfo(
                new DealInfoArtCode(aptCode),
                new DealInfoData(
                        apartmentName,
                        lat, lng,
                        toAdress(dong, roadName, jibun),
                        floor,
                        toDealAmountFormat(dealAmount),
                        toLocalDate(year, month, day))
        );
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DealInfoArtCode {
        private String artCode;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DealInfoData {
        private String apartmentName;
        private Double lat;
        private Double lng;
        private String address;
        private Integer floor;
        private Long dealAmount;
        private LocalDate dealDate;
    }

    @Override
    public int compareTo(DealInfo o) {
        return Long.compare(o.getDealInfoData().getDealAmount(), this.getDealInfoData().getDealAmount());
    }

    private static String toAdress(String dong, String roadName, String jibun){
        StringBuffer address = new StringBuffer();
        address.append(dong).append(" ").append(roadName).append(" ").append(jibun);
        return address.toString();
    }

    private static LocalDate toLocalDate(Integer year, Integer month, Integer day){
        return LocalDate.of(year, month, day);
    }

    private static Long toDealAmountFormat(String dealAmount){
        return Long.parseLong(dealAmount.replace(",", "")) * 10_000;
    }
}
