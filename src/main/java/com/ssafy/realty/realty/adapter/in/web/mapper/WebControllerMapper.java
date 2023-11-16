package com.ssafy.realty.realty.adapter.in.web.mapper;

import com.ssafy.realty.realty.adapter.in.web.payload.SavePayload;
import com.ssafy.realty.realty.adapter.in.web.payload.UpdatePayload;
import com.ssafy.realty.realty.adapter.in.web.payload.wrap.MarkerPayloads;
import com.ssafy.realty.realty.application.port.common_dto.MarkerDto;
import com.ssafy.realty.realty.adapter.in.web.payload.MarkerPayload;
import com.ssafy.realty.realty.application.port.in.DeleteDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import com.ssafy.realty.realty.application.port.in.dto.SaveTemporaryDto;
import com.ssafy.realty.realty.application.port.in.dto.UpdateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebControllerMapper {

    public final ModelMapper modelMapper;

    public MarkerDtos mapToMarkerDtos(MarkerPayloads markerPayloads) {
        List<MarkerDto> markers = markerPayloads.getMarkers()
                .stream()
                .map(this::mapToMakerDto)
                .collect(Collectors.toList());

        return new MarkerDtos(markers);
    }

    public MarkerDto mapToMakerDto(MarkerPayload markerPayload) {
        return modelMapper.map(markerPayload, MarkerDto.class);
    }

    public SaveDto mapToSaveDto(Long userId, SavePayload savePayload) {
        return new SaveDto(userId, savePayload.getTitle(),
                mapToMarkerDtos(new MarkerPayloads(savePayload.getMarkers()))
        );
    }

    public UpdateDto mapToUpdateDto(Long userId, Long customId, UpdatePayload updatePayload){
        return UpdateDto.builder()
                .userId(userId)
                .customId(customId)
                .title(updatePayload.getTitle())
                .markers(mapToMarkerDtos(new MarkerPayloads(updatePayload.getMarkers())))
                .build();
    }

    public SaveTemporaryDto mapToSaveTemporaryDto(Long userId, MarkerPayloads markerPayloads) {
        return new SaveTemporaryDto(userId, mapToMarkerDtos(markerPayloads));
    }

    public DeleteDto mapToDeleteDto(Long userId, Long customId) {
        return new DeleteDto(userId, customId);
    }
}
