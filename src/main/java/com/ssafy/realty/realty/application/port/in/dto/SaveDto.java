package com.ssafy.realty.realty.application.port.in.dto;

import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
