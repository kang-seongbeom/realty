package com.ssafy.realty.realty.application.port.in.dto;

import com.ssafy.realty.realty.application.port.in.dto.wrap.MarkerDtos;
import lombok.Data;

import java.util.List;

@Data
public class SaveDto {
    Long userId;
    String title;
    MarkerDtos markers;

    public SaveDto(Long userId, String title, MarkerDtos markers) {
        this.userId = userId;
        this.title = title;
        this.markers = markers;
    }
}
