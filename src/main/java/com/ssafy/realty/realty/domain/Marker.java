package com.ssafy.realty.realty.domain;

import com.ssafy.realty.common.TransportationType;
import lombok.*;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Value
public class Marker {
    MarkerId markerId;
    MarkerData markerData;

    public static Marker init(Double lat, Double lng, String address,
                              String dateLower, String dateUpper,
                              Long dealAmountLower, Long dealAmountUpper,
                              Double areaLower, Double areaUpper,
                              List<String[]> transportations) {
        return new Marker(null,
                new MarkerData(lat, lng, new MarkerData.MarkerAddress(address),
                        MarkerData.initMarkerFilter(
                                dateLower, dateUpper,
                                dealAmountLower, dealAmountUpper,
                                areaLower, areaUpper,
                                transportations
                        )
                )
        );
    }

    public static Marker init(Long markerId,
                              Double lat, Double lng, String address,
                              MarkerData.MarkerFilter filter) {
        return new Marker(
                new MarkerId(markerId),
                new MarkerData(
                        lat, lng,
                        new MarkerData.MarkerAddress(address),
                        filter)
        );
    }

    @Value
    private static class MarkerId {
        Long value;
    }

    @Value
    public static class MarkerData {
        Double lat;
        Double lng;
        MarkerAddress address;
        MarkerFilter filter;

        static MarkerFilter initMarkerFilter(String dateLower, String dateUpper,
                                             Long dealAmountLower, Long dealAmountUpper,
                                             Double areaLower, Double areaUpper,
                                             List<String[]> transportations) {
            return new MarkerFilter(
                    MarkerData.MarkerFilter.initDateRange(dateLower, dateUpper),
                    MarkerData.MarkerFilter.initDealAmountRange(dealAmountLower, dealAmountUpper),
                    MarkerData.MarkerFilter.initAreaRange(areaLower, areaUpper),
                    MarkerData.MarkerFilter.initTransportations(transportations)
            );
        }

        @Getter
        public static class MarkerAddress {
            private String sidoName;
            private String gugunName;
            private String dongName;

            public String getFullAddress(){
                StringBuffer buffer = new StringBuffer();
                buffer.append(sidoName).append(" ")
                        .append(gugunName).append(" ")
                        .append(dongName);
                return buffer.toString();
            }

            public MarkerAddress(String address){
                String[] names = split(address);
                setNames(names);
            }

            private void setNames(String[] names) {
                setSidoName(names);
                int index = setGugunName(names);
                setDongName(names, index);
            }

            public String[] split(String address) {
                return address.split(" ");
            }

            private void setSidoName(String[] names) {
                if (names.length == 0) {
                    throw new NoResultException("sido를 찾을 수 없습니다.");
                }

                String s = names[0];

                if (s.equals("서울")) {
                    this.sidoName = "서울특별시";
                } else if (s.equals("부산")) {
                    this.sidoName = "부산광역시";
                } else if (s.equals("대구")) {
                    this.sidoName = "대구광역시";
                } else if (s.equals("인천")) {
                    this.sidoName = "인천광역시";
                } else if (s.equals("광주")) {
                    this.sidoName = "광주광역시";
                } else if (s.equals("대전")) {
                    this.sidoName = "대전광역시";
                } else if (s.equals("울산")) {
                    this.sidoName = "울산광역시";
                } else if (s.equals("세종")) {
                    this.sidoName = "세종특별자치시";
                } else if (s.equals("경기")) {
                    this.sidoName = "경기도";
                } else if (s.equals("강원특별자치도")) {
                    this.sidoName = "강원도";
                } else if (s.equals("충북")) {
                    this.sidoName = "충청북도";
                } else if (s.equals("충남")) {
                    this.sidoName = "충청남도";
                } else if (s.equals("전북")) {
                    this.sidoName = "전라북도";
                } else if (s.equals("전남")) {
                    this.sidoName = "전라남도";
                } else if (s.equals("경북")) {
                    this.sidoName = "경상북도";
                } else if (s.equals("경남")) {
                    this.sidoName = "경상남도";
                } else if (s.equals("제주")) {
                    this.sidoName = "제주특별자치도";
                } else {
                    this.sidoName = names[0];
                }
            }

            private int setGugunName(String[] names) {
                if (names.length < 3) {
                    throw new NoResultException("gugun를 찾을 수 없습니다.");
                }

                String secondLast = names[1];
                String thirdLast = names[2];

                if (secondLast.endsWith("시") && thirdLast.endsWith("구")) {
                    this.gugunName = secondLast + " " + thirdLast;
                    return 3;
                }

                this.gugunName = secondLast;
                return 2;
            }

            private void setDongName(String[] names, int index){
                StringBuffer buffer = new StringBuffer();
                String jibunRegex = "[0-9-]";
                String jibun = names[names.length-1].replaceAll(jibunRegex, "");

                if(jibun.length() == 0){
                    for(int i=index; i<names.length-1; i++){
                        buffer.append(names[i]).append(" ");
                    }
                }else{
                    for(int i=index; i<names.length; i++){
                        buffer.append(names[i]).append(" ");
                    }
                }
                this.dongName = buffer.toString().trim();
            }
        }

        @Value
        public static class MarkerFilter {
            DateRange dayRange;
            DealAmountRange dealAmountRange;
            AreaRange areaRange;
            List<Transportation> transportations;

            @Getter
            @AllArgsConstructor
            public static class Range<T> {
                T lower;
                T upper;
            }

            @Value
            public static class DateRange {
                Range<LocalDate> range;
            }

            @Value
            public static class DealAmountRange {
                Range<Long> range;
            }

            @Value
            public static class AreaRange {
                Range<Double> range;
            }

            @Value
            public static class Transportation {
                TransportationType type;
                Integer time;
            }

            private static DateRange initDateRange(String lower, String upper) {
                if (lower == null) lower = "1900-01-01";
                if (upper == null) upper = "2999-12-31";

                return new DateRange(new Range<>(
                        LocalDate.parse(lower, DateTimeFormatter.ISO_DATE),
                        LocalDate.parse(upper, DateTimeFormatter.ISO_DATE))
                );
            }

            private static DealAmountRange initDealAmountRange(Long lower, Long upper) {
                if (lower == null) lower = 0L;
                if (upper == null) upper = 999_999_999_999L;

                return new DealAmountRange(new Range<>(lower, upper));
            }

            private static AreaRange initAreaRange(Double lower, Double upper) {
                if (lower == null) lower = 0.0;
                if (upper == null) upper = 9_999_999.0;

                return new AreaRange(new Range<>(lower, upper));
            }

            private static List<Transportation> initTransportations(List<String[]> transInfo) {
                List<Transportation> result = new ArrayList<>();

                for (String[] info : transInfo) {
                    if (info.length != 2) throw new IllegalArgumentException("잘못된 데이터가 포함되어 있습니다.");

                    TransportationType type = TransportationType.findByTypeKey(info[0]);
                    Integer time = Integer.parseInt(info[1]);

                    result.add(new Transportation(type, time));
                }
                return result;
            }
        }
    }
}
