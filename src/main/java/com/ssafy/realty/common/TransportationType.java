package com.ssafy.realty.common;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum TransportationType {
    WALK("도보"), BYCYCLE("자건거"), OWN_CAR("자가용");

    private final String value;

    TransportationType(String value) {
        this.value = value;
    }

    public static TransportationType findByValue(String value){
        return Arrays.stream(TransportationType.values())
                .filter(t -> t.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("교통수단을 찾을 수 없습니다."));
    }
}
