package com.ssafy.realty.realty.application.service.mapper;

import com.ssafy.realty.realty.application.port.common_dto.MarkerDto;
import com.ssafy.realty.realty.application.port.in.DeleteDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import com.ssafy.realty.realty.application.port.in.dto.SaveTemporaryDto;
import com.ssafy.realty.realty.application.port.in.dto.UpdateDto;
import com.ssafy.realty.realty.application.port.out.dto.TotalHistoryDealInfoDto;
import com.ssafy.realty.realty.application.port.out.dto.VicinityHomeInfosDto;
import com.ssafy.realty.realty.application.port.out.dto.wrap.TotalHistoryDealInfoDtos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.domain.*;
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
        MarkerDto.DtoMarkerFilter filter = markerDto.getFilter();

        return Marker.init(
                markerDto.getLat(), markerDto.getLng(), markerDto.getAddress(),
                filter.getDate() != null ? filter.getDate().getLower() : null,
                filter.getDate() != null ? filter.getDate().getUpper() : null,
                filter.getDealAmount() != null ? filter.getDealAmount().getLower() : null,
                filter.getDealAmount() != null ? filter.getDealAmount().getUpper() : null,
                filter.getArea() != null ? filter.getArea().getLower() : null,
                filter.getArea() != null ? filter.getArea().getUpper() : null,
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
        if (dealInfos.getDealInfos().isEmpty()) {
            return TotalHistoryDealInfoDtos.builder().build();
        }

        return TotalHistoryDealInfoDtos
                .builder()
                .aptCode(dealInfos.getDealInfos().get(0).getDealInfoArtCode().getArtCode())
                .lat(dealInfos.getDealInfos().get(0).getDealInfoData().getLat())
                .lng(dealInfos.getDealInfos().get(0).getDealInfoData().getLng())
                .address(dealInfos.getDealInfos().get(0).getDealInfoData().getAddress())
                .data(mapToTotalHistoryDealInfoDatas(dealInfos))
                .build();

    }

    private List<TotalHistoryDealInfoDto> mapToTotalHistoryDealInfoDatas(DealInfos dealInfos) {
        List<TotalHistoryDealInfoDto> data = dealInfos.getDealInfos()
                .stream()
                .map(this::mapToTotalHistoryDealInfo)
                .collect(Collectors.toList());
        return data;
    }

    public Save mapToSave(SaveDto saveDto) {
        return Save.init(saveDto.getUserId(), saveDto.getTitle(), mapToMarkers(saveDto.getMarkers()));
    }

    public Update mapToUpdate(UpdateDto updateDto) {
        return Update.init(updateDto.getUserId(), updateDto.getCustomId(), updateDto.getTitle(), mapToMarkers(updateDto.getMarkers()));
    }


    public SaveTemporary mapToSaveTemporary(SaveTemporaryDto saveTemporaryDto) {
        return SaveTemporary.init(saveTemporaryDto.getId(), mapToMarkers(saveTemporaryDto.getMarkers()));
    }

    public Markers mapToMarkers(MarkerDtos markerDtos) {
        List<Marker> data = markerDtos.getData()
                .stream()
                .map(this::mapToMarker)
                .collect(Collectors.toList());

        return new Markers(data);
    }

    public MarkerDtos mapToMarkerDtos(Markers markers) {
        List<MarkerDto> data = markers.getMarkers()
                .stream()
                .map(this::mapToMarkerDto)
                .collect(Collectors.toList());

        return new MarkerDtos(data);
    }

    public Delete mapToDelete(DeleteDto deleteDto) {
        return Delete.init(deleteDto.getUserId(), deleteDto.getCustomId());
    }

    private MarkerDto mapToMarkerDto(Marker marker) {
        return MarkerDto.builder()
                .lat(marker.getMarkerData().getLat())
                .lng(marker.getMarkerData().getLng())
                .address(marker.getMarkerData().getAddress())
                .filter(mapToDtoMarkerFilter(marker.getMarkerData().getFilter()))
                .build();
    }

    private MarkerDto.DtoMarkerFilter mapToDtoMarkerFilter(Marker.MarkerData.MarkerFilter markerFilter) {
        return MarkerDto.DtoMarkerFilter
                .builder()
                .date(mapToPayloadDateRange(markerFilter.getDayRange()))
                .dealAmount(mapToPayloadDealAmountRange(markerFilter.getDealAmountRange()))
                .area(mapToPayloadAreaRange(markerFilter.getAreaRange()))
                .transportations(mapToPayloadTransportations(markerFilter.getTransportations()))
                .build();
    }

    private MarkerDto.DtoMarkerFilter.DtoDateRange mapToPayloadDateRange(Marker.MarkerData.MarkerFilter.DateRange date) {
        return new MarkerDto.DtoMarkerFilter.DtoDateRange(date.getRange().getLower().toString(), date.getRange().getUpper().toString());
    }

    private MarkerDto.DtoMarkerFilter.DtoDealAmountRange mapToPayloadDealAmountRange(Marker.MarkerData.MarkerFilter.DealAmountRange dealAmount) {
        return new MarkerDto.DtoMarkerFilter.DtoDealAmountRange(dealAmount.getRange().getLower(), dealAmount.getRange().getUpper());
    }

    private MarkerDto.DtoMarkerFilter.DtoAreaRange mapToPayloadAreaRange(Marker.MarkerData.MarkerFilter.AreaRange area) {
        return new MarkerDto.DtoMarkerFilter.DtoAreaRange(area.getRange().getLower(), area.getRange().getUpper());
    }

    private List<MarkerDto.DtoMarkerFilter.DtoTransportation> mapToPayloadTransportations(List<Marker.MarkerData.MarkerFilter.Transportation> trans) {
        return trans.stream()
                .map(this::mapToPayloadTransportation)
                .collect(Collectors.toList());
    }

    private MarkerDto.DtoMarkerFilter.DtoTransportation mapToPayloadTransportation(Marker.MarkerData.MarkerFilter.Transportation t) {
        return new MarkerDto.DtoMarkerFilter.DtoTransportation(t.getType().toString(), t.getTime());
    }

    private TotalHistoryDealInfoDto mapToTotalHistoryDealInfo(DealInfo dealInfo) {
        return TotalHistoryDealInfoDto
                .builder()
                .floor(dealInfo.getDealInfoData().getFloor())
                .dealAmount(dealInfo.getDealInfoData().getDealAmount())
                .dealDate(dealInfo.getDealInfoData().getDealDate())
                .build();
    }

    private List<String[]> getTransportations(MarkerDto markerDto) {
        List<String[]> transportations = new ArrayList<>();
        if (markerDto.getFilter().getTransportations() != null) {
            for (MarkerDto.DtoMarkerFilter.DtoTransportation p : markerDto.getFilter().getTransportations()) {
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
