package com.ssafy.realty.realty.application.service;

import com.ssafy.realty.realty.application.port.in.QueryRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.dto.MarkerDto;
import com.ssafy.realty.realty.application.port.out.QueryRealtyPort;
import com.ssafy.realty.realty.application.port.out.dto.wrap.TotalHistoryDealInfos;
import com.ssafy.realty.realty.application.port.out.dto.wrap.VicinityHomeInfoDtos;
import com.ssafy.realty.realty.application.service.mapper.QueryRealtyServiceMapper;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class QueryRealtyService implements QueryRealtyUseCase {

    private final QueryRealtyPort queryRealtyPort;
    private final QueryRealtyServiceMapper queryRealtyServiceMapper;

    @Override
    public VicinityHomeInfoDtos queryMarkerVicinityHome(MarkerDto markerDto) {
        Marker marker = queryRealtyServiceMapper.mapToMarker(markerDto);
        TotalVicinityHomeInfos total = queryRealtyPort.queryMarkerVicinityHome(marker);
        return queryRealtyServiceMapper.mapToVicinityHomeInfoDtos(total);
    }

    @Override
    public TotalHistoryDealInfos queryTotalHistory(String aptCode) {
        DealInfos histories = queryRealtyPort.queryTotalHistory(aptCode);
        return queryRealtyServiceMapper.mapToTotalHistoryDealInfos(histories);
    }
}
