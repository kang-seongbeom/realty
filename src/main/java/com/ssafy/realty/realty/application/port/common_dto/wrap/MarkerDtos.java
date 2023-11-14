package com.ssafy.realty.realty.application.port.common_dto.wrap;

import com.ssafy.realty.realty.application.port.common_dto.MarkerDto;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class MarkerDtos {
    List<MarkerDto> data;
}
