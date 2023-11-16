package com.ssafy.realty.custom_deal.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class OwnCustomCatalogDto {
    private Long userId;
    private Pageable pageable;
}
