package com.ssafy.realty.realty.application.port.out.dto.wrap;

import com.ssafy.realty.realty.application.port.out.dto.VicinityHomeInfosDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
public class VicinityHomeInfoDtos {
    List<VicinityHomeInfosDto> data;
}
