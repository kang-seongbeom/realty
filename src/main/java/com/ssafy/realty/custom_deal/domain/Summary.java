package com.ssafy.realty.custom_deal.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Summary {

    private CustomSummaryId customSummaryId;
    private CustomSummaryData customSummaryData;

    public static Summary init(Long id, String author, String title, Integer look, Integer star, LocalDate createDate){
        return new Summary(
                new CustomSummaryId(id),
                new CustomSummaryData(author, title, look, star, createDate)
        );
    }


    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CustomSummaryId{
        Long value;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CustomSummaryData{
        String author;
        String title;
        Integer look;
        Integer start;
        LocalDate createDate;
    }
}
