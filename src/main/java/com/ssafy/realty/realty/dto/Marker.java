package com.ssafy.realty.realty.dto;

import com.ssafy.realty.realty.dto.type.RentType;
import com.ssafy.realty.realty.dto.type.TransportationType;
import lombok.Data;

import java.util.List;

@Data
public class Marker {
    Double lat;
    Double lng;
    String address;
    List<MarkerFilter> filters;

    @Data
    public class MarkerFilter {
        String realtyType;
        RentType rentType;
        Integer maxRentCost;
        Long maxLongTermRentCost;
        Long maxSaleCost;
        Integer maxMaintenanceCost;
        Integer contractTerm;
        List<Transportation> transportations;
    }

    @Data
    public class Transportation {
        TransportationType type;
        Integer time;
    }
}
