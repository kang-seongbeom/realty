package com.ssafy.realty.custom_deal.application.port.out.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CustomSummaryDto {

    Long id;
    String author;
    String title;
    Integer look;
    Integer start;
    LocalDate createDate;
}
