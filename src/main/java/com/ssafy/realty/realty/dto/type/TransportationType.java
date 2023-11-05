package com.ssafy.realty.realty.dto.type;

public enum TransportationType {
    MONTH_TERM("도보"), LONG_TERM("자건거"), SALE("자가용");

    private final String type;

    TransportationType(String type) {
        this.type = type;
    }
}
