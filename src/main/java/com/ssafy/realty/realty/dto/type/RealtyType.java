package com.ssafy.realty.realty.dto.type;

public enum RealtyType {
    OFFICETEL("오피스텔"), APARTMENT("아파트"), VILLA("빌라"), ;

    private final String type;

    RealtyType(String type) {
        this.type = type;
    }
}

