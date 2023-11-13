package com.ssafy.realty.realty.application.service.mapper;

import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.application.port.out.dto.TotalHistoryDealInfo;
import com.ssafy.realty.realty.application.port.out.dto.VicinityHomeInfosDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.TotalHistoryDealInfos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.domain.DealInfo;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.VicinityHomeInfo;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;
import com.ssafy.realty.realty.domain.wrap.VicinityHomeInfos;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryRealtyServiceMapper {

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

    public VicinityHomeInfoDtos mapToVicinityHomeInfoDtos(TotalVicinityHomeInfos total) {
        List<VicinityHomeInfosDto> data = new ArrayList<>();

        for (VicinityHomeInfos infos : total.getTotal()) {
            data.add(mapToVicinityHomeInfosDto(infos));
        }
        return new VicinityHomeInfoDtos(data);
    }

    public TotalHistoryDealInfos mapToTotalHistoryDealInfos(DealInfos dealInfos){
        List<TotalHistoryDealInfo> data = dealInfos.getDealInfos()
                .stream()
                .map(d -> mapToTotalHistoryDealInfo(d))
                .collect(Collectors.toList());
        return new TotalHistoryDealInfos(data);
    }

    private TotalHistoryDealInfo mapToTotalHistoryDealInfo(DealInfo dealInfo){
        return TotalHistoryDealInfo
                .builder()
                .aptCode(dealInfo.getDealInfoArtCode().getArtCode())
                .apartmentName(dealInfo.getDealInfoData().getApartmentName())
                .lat(dealInfo.getDealInfoData().getLat())
                .lng(dealInfo.getDealInfoData().getLng())
                .address(dealInfo.getDealInfoData().getAddress())
                .floor(dealInfo.getDealInfoData().getFloor())
                .dealAmount(dealInfo.getDealInfoData().getDealAmount())
                .dealDate(dealInfo.getDealInfoData().getDealDate())
                .build();
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
}
