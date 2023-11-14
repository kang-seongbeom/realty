package com.ssafy.realty.realty.adapter.in.web.mapper;

import com.ssafy.realty.realty.adapter.in.web.payload.SavePayload;
import com.ssafy.realty.realty.application.port.common_dto.MarkerDto;
import com.ssafy.realty.realty.adapter.in.web.payload.MarkerPayload;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebControllerMapper {

    public final ModelMapper modelMapper;

    public MarkerDto mapToMakerDto(MarkerPayload markerPayload){
        return modelMapper.map(markerPayload, MarkerDto.class);
    }

    public SaveDto mapToSaveDto(Long userId, SavePayload savePayload){
        List<MarkerDto> data = savePayload.getMarkers()
                .stream()
                .map(this::mapToMakerDto)
                .collect(Collectors.toList());
        return new SaveDto(userId, savePayload.getTitle(), new MarkerDtos(data));
    }
}
