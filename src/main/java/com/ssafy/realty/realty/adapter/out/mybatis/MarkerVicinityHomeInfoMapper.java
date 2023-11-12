package com.ssafy.realty.realty.adapter.out.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkerVicinityHomeInfoMapper {

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
