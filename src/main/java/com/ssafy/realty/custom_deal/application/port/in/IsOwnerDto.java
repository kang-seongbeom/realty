package com.ssafy.realty.custom_deal.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IsOwnerDto {
    private Long userId;
    private Long customId;
}
