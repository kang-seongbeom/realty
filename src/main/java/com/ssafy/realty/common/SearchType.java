package com.ssafy.realty.common;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum SearchType {
    ALL("all"), TITLE("title"), AUTHOR("author");

    private final String value;

    SearchType(String value) {
        this.value = value;
    }

    public static SearchType findBySearchValue(String s){
        if(s == null) return ALL;

        return Arrays.stream(SearchType.values())
                .filter(v -> v.value.equals(s.toLowerCase()))
                .findFirst()
                .orElse(ALL);
    }

    public String getValue() {
        return value;
    }
}
