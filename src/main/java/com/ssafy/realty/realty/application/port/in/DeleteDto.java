package com.ssafy.realty.realty.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteDto {
    private Long userId;
    private Long customId;
}
