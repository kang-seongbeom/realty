package com.ssafy.realty.realty.domain.type;

public enum TransportationType {
    WALK("도보"), BYCYCLE("자건거"), OWN_CAR("자가용");

    private final String type;

    TransportationType(String type) {
        this.type = type;
    }
}
