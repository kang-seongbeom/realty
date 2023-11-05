package com.ssafy.realty.realty.dto.type;

public enum RentType {
    MONTH_TERM("월세"), LONG_TERM("전세"), SALE("매매");

    private final String type;

    RentType(String type) {
        this.type = type;
    }
}

