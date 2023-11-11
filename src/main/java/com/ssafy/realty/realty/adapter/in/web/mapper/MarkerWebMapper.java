package com.ssafy.realty.realty.adapter.in.web.mapper;

import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.adapter.in.web.payload.MarkerPayload;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarkerWebMapper {

    public final ModelMapper modelMapper;

    public MarkerDto mapToMakerDto(MarkerPayload markerPayload){
        return modelMapper.map(markerPayload, MarkerDto.class);
    }
}
