package com.ssafy.realty.realty.application.service.mapper;

import com.ssafy.realty.realty.application.port.common_dto.MarkerDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import com.ssafy.realty.realty.application.port.out.dto.TotalHistoryDealInfoDto;
import com.ssafy.realty.realty.application.port.out.dto.VicinityHomeInfosDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.TotalHistoryDealInfoDtos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.domain.DealInfo;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.Save;
import com.ssafy.realty.realty.domain.VicinityHomeInfo;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.Markers;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;
import com.ssafy.realty.realty.domain.wrap.VicinityHomeInfos;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RealtyServiceMapper {

    public Marker mapToMarker(MarkerDto markerDto) {
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

    public TotalHistoryDealInfoDtos mapToTotalHistoryDealInfos(DealInfos dealInfos) {
        List<TotalHistoryDealInfoDto> data = dealInfos.getDealInfos()
                .stream()
                .map(this::mapToTotalHistoryDealInfo)
                .collect(Collectors.toList());
        return new TotalHistoryDealInfoDtos(data);
    }

    public Save mapToSave(SaveDto saveDto) {
        MarkerDtos markers = saveDto.getMarkers();
        List<Marker> data = markers.getData()
                .stream()
                .map(this::mapToMarker)
                .collect(Collectors.toList());

        return Save.init(saveDto.getUserId(), saveDto.getTitle(), new Markers(data));
    }

    public MarkerDtos mapToMarkerDtos(Markers markers) {
        List<MarkerDto> data = markers.getMarkers()
                .stream()
                .map(this::mapToMarkerDto)
                .collect(Collectors.toList());

        return new MarkerDtos(data);
    }

    private MarkerDto mapToMarkerDto(Marker marker) {
        return MarkerDto.builder()
                .lat(marker.getMarkerData().getLat())
                .lng(marker.getMarkerData().getLng())
                .address(marker.getMarkerData().getAddress())
                .filter(mapToDtoMarkerFilter(marker.getMarkerData().getFilter()))
                .build();
    }

    private MarkerDto.DtoMarkerFilter mapToDtoMarkerFilter(Marker.MarkerData.MarkerFilter markerFilter){
        return MarkerDto.DtoMarkerFilter
                .builder()
                .date(mapToPayloadDateRange(markerFilter.getDayRange()))
                .dealAmount(mapToPayloadDealAmountRange(markerFilter.getDealAmountRange()))
                .area(mapToPayloadAreaRange(markerFilter.getAreaRange()))
                .transportations(mapToPayloadTransportations(markerFilter.getTransportations()))
                .build();
    }

    private MarkerDto.DtoMarkerFilter.PayloadDateRange mapToPayloadDateRange(Marker.MarkerData.MarkerFilter.DateRange date) {
        return new MarkerDto.DtoMarkerFilter.PayloadDateRange(date.getRange().getLower().toString(), date.getRange().getUpper().toString());
    }

    private MarkerDto.DtoMarkerFilter.PayloadDealAmountRange mapToPayloadDealAmountRange(Marker.MarkerData.MarkerFilter.DealAmountRange dealAmount) {
        return new MarkerDto.DtoMarkerFilter.PayloadDealAmountRange(dealAmount.getRange().getLower(), dealAmount.getRange().getUpper());
    }

    private MarkerDto.DtoMarkerFilter.PayloadAreaRange mapToPayloadAreaRange(Marker.MarkerData.MarkerFilter.AreaRange area){
        return new MarkerDto.DtoMarkerFilter.PayloadAreaRange(area.getRange().getLower(), area.getRange().getUpper());
    }

    private List<MarkerDto.DtoMarkerFilter.PayloadTransportation> mapToPayloadTransportations(List<Marker.MarkerData.MarkerFilter.Transportation> trans) {
        return trans.stream()
                .map(this::mapToPayloadTransportation)
                .collect(Collectors.toList());
    }

    private MarkerDto.DtoMarkerFilter.PayloadTransportation mapToPayloadTransportation(Marker.MarkerData.MarkerFilter.Transportation t) {
        return new MarkerDto.DtoMarkerFilter.PayloadTransportation(t.getType().toString(), t.getTime());
    }

    private TotalHistoryDealInfoDto mapToTotalHistoryDealInfo(DealInfo dealInfo) {
        return TotalHistoryDealInfoDto
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

    private List<String[]> getTransportations(MarkerDto markerDto) {
        List<String[]> transportations = new ArrayList<>();
        if (markerDto.getFilter().getTransportations() != null) {
            for (MarkerDto.DtoMarkerFilter.PayloadTransportation p : markerDto.getFilter().getTransportations()) {
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
