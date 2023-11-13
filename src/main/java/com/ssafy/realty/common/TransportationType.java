package com.ssafy.realty.common;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum TransportationType {
    WALK("walk", 0.01), BYCYCLE("bicycle", 0.0333), OWN_CAR("car", 0.0833);

    private final String key;
    private final Double value;

    TransportationType(String key, Double value) {
        this.key = key;
        this.value = value;
    }

    public static TransportationType findByTypeKey(String key){
        return Arrays.stream(TransportationType.values())
                .filter(t -> t.key.equals(key.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("교통수단을 찾을 수 없습니다."));
    }

    public Double getDistance(Integer time) {
        return this.value * time;
    }
}
