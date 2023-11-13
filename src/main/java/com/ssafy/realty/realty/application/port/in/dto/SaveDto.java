package com.ssafy.realty.realty.application.port.in.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaveDto {
    String title;
    List<MarkerDto> markers;
}
