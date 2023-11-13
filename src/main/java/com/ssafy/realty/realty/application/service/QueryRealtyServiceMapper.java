package com.ssafy.realty.realty.application.service;

import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.application.port.out.dto.VicinityHomeInfosDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.VicinityHomeInfo;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;
import com.ssafy.realty.realty.domain.wrap.VicinityHomeInfos;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryRealtyServiceMapper {

    public VicinityHomeInfoDtos mapToVicinityHomeInfoDtos(TotalVicinityHomeInfos total) {
        List<VicinityHomeInfosDto> data = new ArrayList<>();

        for (VicinityHomeInfos infos : total.getTotal()) {
            data.add(mapToVicinityHomeInfosDto(infos));
        }
        return new VicinityHomeInfoDtos(data);
    }

    private VicinityHomeInfosDto mapToVicinityHomeInfosDto(VicinityHomeInfos infos) {
        return VicinityHomeInfosDto
                .builder()
                .type(infos.getType())
                .time(infos.getTime())
                .homeSummaryInfos(mapToHomeSummaryInfos(infos))
                .build();
    }

    private List<VicinityHomeInfosDto.homeSummaryInfo> mapToHomeSummaryInfos(VicinityHomeInfos vicinities) {
        List<VicinityHomeInfosDto.homeSummaryInfo> homeInfos = new ArrayList<>();

        for (VicinityHomeInfo info : vicinities.getVicinityHomeInfos()) {
            homeInfos.add(mapToHomeSummaryInfo(info));
        }
        return homeInfos;
    }

    private VicinityHomeInfosDto.homeSummaryInfo mapToHomeSummaryInfo(VicinityHomeInfo info) {
        return VicinityHomeInfosDto.homeSummaryInfo.builder()
                .aptCode(info.getVicinityHomeInfoId().getAptCode())
                .apartmentName(info.getVicinityHomeInfoData().getApartmentName())
                .lat(info.getVicinityHomeInfoData().getLat())
                .lng(info.getVicinityHomeInfoData().getLng())
                .address(info.getVicinityHomeInfoData().getAddress())
                .totalDealAmount(info.getVicinityHomeInfoData().getTotalDealAmount())
                .maxDealAmount(info.getVicinityHomeInfoData().getMaxDealAmount())
                .minDealAmount(info.getVicinityHomeInfoData().getMinDealAmount())
                .avgDealAmount(info.getVicinityHomeInfoData().getAvgDealAmount())
                .avgArea(info.getVicinityHomeInfoData().getAvgArea())
                .build();
    }

    public Marker mapToMarker(MarkerDto markerDto){
        return Marker.init(
                markerDto.getLat(), markerDto.getLng(), markerDto.getAddress(),
                markerDto.getFilter().getDate() != null ? markerDto.getFilter().getDate().getLower() : null,
                markerDto.getFilter().getDate() != null ? markerDto.getFilter().getDate().getUpper() : null,
                markerDto.getFilter().getDealAmount() != null ? markerDto.getFilter().getDealAmount().getLower() : null,
                markerDto.getFilter().getDealAmount() != null ? markerDto.getFilter().getDealAmount().getUpper() : null,
                markerDto.getFilter().getArea() != null ? markerDto.getFilter().getArea().getLower() : null,
                markerDto.getFilter().getArea() != null ? markerDto.getFilter().getArea().getUpper() : null,
                getTransportations(markerDto)
        );
    }

    private List<String[]> getTransportations(MarkerDto markerDto){
        List<String[]> transportations = new ArrayList<>();
        if(markerDto.getFilter().getTransportations() != null){
            for(MarkerDto.DtoMarkerFilter.PayloadTransportation p: markerDto.getFilter().getTransportations()){
                String[] trans = new String[2];
                trans[0] = p.getType();
                trans[1] = String.valueOf(p.getTime());
                transportations.add(trans);
            }
        }
        return transportations;
    }
}
