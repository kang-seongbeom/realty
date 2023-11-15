package com.ssafy.realty.realty.application.port.in.dto;

import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveTemporaryDto {
    Long id;
    MarkerDtos markers;
}
