package com.ssafy.realty.custom_deal.application.port.in.dto;

import com.ssafy.realty.common.SearchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class SearchCustomDto {
    SearchType searchType;
    String value;
    Pageable pageable;
}
