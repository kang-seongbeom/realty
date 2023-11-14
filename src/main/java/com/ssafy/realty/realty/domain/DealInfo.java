package com.ssafy.realty.realty.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
public class DealInfo implements Comparable<DealInfo> {
    DealInfoArtCode dealInfoArtCode;
    DealInfoData dealInfoData;

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

    @Value
    public static class DealInfoArtCode {
        String artCode;
    }

    @Value
    public static class DealInfoData {
        String apartmentName;
        Double lat;
        Double lng;
        String address;
        Integer floor;
        Long dealAmount;
        LocalDate dealDate;
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
