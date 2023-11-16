package com.ssafy.realty.realty.application.port.in.dto;

import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import lombok.*;

import javax.validation.constraints.Size;


@Value
@Builder
public class UpdateDto{
    Long userId;
    Long customId;
    String title;

    MarkerDtos markers;

}
