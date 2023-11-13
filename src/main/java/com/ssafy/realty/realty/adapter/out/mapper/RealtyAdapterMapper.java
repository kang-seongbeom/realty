package com.ssafy.realty.realty.adapter.out.mapper;

import com.ssafy.realty.common.TransportationType;
import com.ssafy.realty.realty.adapter.out.entity.*;
import com.ssafy.realty.realty.adapter.out.entity.mybatis.MarkerVicinityHomeInfo;
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
public class RealtyAdapterMapper {

    public TotalVicinityHomeInfos mapToTotalVicinityHomeInfos(List<VicinityHomeInfos> infos) {
        return new TotalVicinityHomeInfos(infos);
    }

    public VicinityHomeInfos mapToVicinityHomeInfos(List<MarkerVicinityHomeInfo> infos,
                                                    TransportationType type, Integer time) {
        List<VicinityHomeInfo> elements = infos.stream()
                .map(this::mapToVicinityHomeInfo)
                .collect(Collectors.toList());
        return new VicinityHomeInfos(elements, type, time);
    }

    public DealInfos mapToDealInfos(HouseInfoJpaEntity info){
        List<DealInfo> data = new ArrayList<>();

        for(HouseDealJpaEntity deal : info.getHousedeals()){
            DealInfo init = DealInfo.init(info.getAptCode(), info.getApartmentName(),
                    info.getLat(), info.getLng(),
                    info.getDong(), info.getRoadName(),
                    info.getJibun(), deal.getFloor(), deal.getDealAmount(),
                    deal.getDealYear(), deal.getDealMonth(), deal.getDealDay()
            );
            data.add(init);
        }
        return new DealInfos(data);
    }

    public CustomJpaEntity mapToCustomJpaEntity(Save save){
        CustomJpaEntity customJpa = CustomJpaEntity
                .builder()
                .title(save.getSaveData().getTitle())
                .build();

        customJpa.setMarkers(getMarkerJpaEntities(save.getSaveData().getMarkers()));
        return customJpa;
    }

    private List<MarkerJpaEntity> getMarkerJpaEntities(Markers markers) {
        return markers.getMarkers()
                .stream()
                .map(this::mapToMarkerJpaEntity)
                .collect(Collectors.toList());
    }

    private MarkerJpaEntity mapToMarkerJpaEntity(Marker marker){
        MarkerJpaEntity markerJpa = MarkerJpaEntity
                .builder()
                .lat(marker.getMarkerData().getLat())
                .lng(marker.getMarkerData().getLng())
                .address(marker.getMarkerData().getAddress())
                .build();

        markerJpa.setFilter(getFilterJpaEntity(marker.getMarkerData().getFilter()));
        return markerJpa;
    }

    private FilterJpaEntity getFilterJpaEntity(Marker.MarkerData.MarkerFilter filter) {
        FilterJpaEntity filterJpa = FilterJpaEntity
                .builder()
                .dateLower(filter.getDayRange().getRange().getLower())
                .dateUpper(filter.getDayRange().getRange().getUpper())
                .dealAmountLower(filter.getDealAmountRange().getRange().getLower())
                .dealAmountUpper(filter.getDealAmountRange().getRange().getUpper())
                .areaLower(filter.getAreaRange().getRange().getLower())
                .areaUpper(filter.getAreaRange().getRange().getUpper())
                .build();

        filterJpa.setTransportations(getTransportationJpaEntities(filter));
        return filterJpa;
    }

    private List<TransportationJpaEntity> getTransportationJpaEntities(Marker.MarkerData.MarkerFilter filter) {
        return filter.getTransportations()
                .stream()
                .map(this::mapToTransportationJpaEntity)
                .collect(Collectors.toList());
    }

    private TransportationJpaEntity mapToTransportationJpaEntity(Marker.MarkerData.MarkerFilter.Transportation transportation){
        return TransportationJpaEntity
                .builder()
                .type(transportation.getType())
                .time(transportation.getTime())
                .build();
    }

    private VicinityHomeInfo mapToVicinityHomeInfo(MarkerVicinityHomeInfo info) {
        return VicinityHomeInfo.init(
                info.getAptCode(), info.getApartmentName(),
                info.getLat(), info.getLng(),
                info.getAddress(), info.getTotalDealAmount(),
                info.getMaxDealAmount(), info.getMinDealAmount(),
                info.getAvgDealAmount(), info.getAvgArea()
        );
    }
}
