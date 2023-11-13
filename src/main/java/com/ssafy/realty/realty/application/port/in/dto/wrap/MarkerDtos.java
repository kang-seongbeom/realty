package com.ssafy.realty.realty.application.port.in.dto.wrap;

import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MarkerDtos {
    List<MarkerDto> data;

    public MarkerDtos(List<MarkerDto> data) {
        this.data = data;
    }
}
