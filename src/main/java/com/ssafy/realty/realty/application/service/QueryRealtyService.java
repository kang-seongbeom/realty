package com.ssafy.realty.realty.application.service;

import com.ssafy.realty.realty.application.port.in.QueryRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.application.port.out.QueryRealtyPort;
import com.ssafy.realty.realty.domain.DealInfo;
import com.ssafy.realty.realty.domain.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class QueryRealtyService implements QueryRealtyUseCase {

    private final QueryRealtyPort queryRealtyPort;

    @Override
    public void queryMarkerVicinityHome(MarkerDto markerDto) {
        Marker marker = initMarker(markerDto);
        List<DealInfo> dealInfos = queryRealtyPort.queryMarkerVicinityHome(marker);
    }

    private Marker initMarker(MarkerDto markerDto){
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
