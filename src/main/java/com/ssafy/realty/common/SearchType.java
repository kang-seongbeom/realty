package com.ssafy.realty.common;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum SearchType {
    TITLE("title"), AUTHOR("author");

    private final String value;

    SearchType(String value) {
        this.value = value;
    }

    public static SearchType findBySearchValue(String s){
        return Arrays.stream(SearchType.values())
                .filter(v -> v.value.equals(s.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("검색 조건을 찾을 수 없습니다."));
    }

    public String getValue() {
        return value;
    }
}
