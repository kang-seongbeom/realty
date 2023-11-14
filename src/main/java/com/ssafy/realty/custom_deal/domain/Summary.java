package com.ssafy.realty.custom_deal.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
public class Summary {

    CustomSummaryId customSummaryId;
    CustomSummaryData customSummaryData;

    public static Summary init(Long id, String author, String title, Integer look, Integer star, LocalDate createDate){
        return new Summary(
                new CustomSummaryId(id),
                new CustomSummaryData(author, title, look, star, createDate)
        );
    }


    @Value
    public static class CustomSummaryId{
        Long value;
    }

    @Value
    public static class CustomSummaryData{
        String author;
        String title;
        Integer look;
        Integer start;
        LocalDate createDate;
    }
}
