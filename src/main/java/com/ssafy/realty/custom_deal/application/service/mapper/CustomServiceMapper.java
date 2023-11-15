package com.ssafy.realty.custom_deal.application.service.mapper;

import com.ssafy.realty.custom_deal.application.port.in.IsOwnerDto;
import com.ssafy.realty.custom_deal.application.port.out.CustomSummaryDto;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;
import com.ssafy.realty.custom_deal.domain.IsOwner;
import com.ssafy.realty.custom_deal.domain.Summary;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomServiceMapper {

    public CustomSummaryDtos mapToCustomSummaryDtos(Summaries summaries) {
        List<CustomSummaryDto> data = summaries.getData()
                .stream()
                .map(this::mapToCustomSummaryDto)
                .collect(Collectors.toList());

        return new CustomSummaryDtos(data);
    }

    public IsOwner mapToIsOwner(IsOwnerDto isOwnerDto){
        return IsOwner.init(isOwnerDto.getUserId(), isOwnerDto.getCustomId());
    }

    private CustomSummaryDto mapToCustomSummaryDto(Summary summary) {
        return CustomSummaryDto
                .builder()
                .id(summary.getCustomSummaryId().getValue())
                .author(summary.getCustomSummaryData().getAuthor())
                .title(summary.getCustomSummaryData().getTitle())
                .look(summary.getCustomSummaryData().getLook())
                .start(summary.getCustomSummaryData().getStart())
                .createDate(summary.getCustomSummaryData().getCreateDate())
                .build();
    }
}
