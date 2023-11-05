package com.ssafy.realty.realty.dto;

import com.ssafy.realty.realty.dto.type.RealtyType;
import com.ssafy.realty.realty.dto.type.RentType;
import lombok.Data;

@Data
public class Home {
    Long id;
    Double lat;
    Double lng;
    RealtyType realtyType;
    RentType rentType;
    Integer floor;
    String name;
    Long dealAmount;
    Double area;
    String comment;
}
