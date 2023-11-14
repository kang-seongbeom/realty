package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.HouseInfoJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.mybatis.MarkerVicinityHomeInfo;
import com.ssafy.realty.realty.adapter.out.mapper.RealtyAdapterMapper;
import com.ssafy.realty.realty.adapter.out.repository.CustomJpaRepository;
import com.ssafy.realty.realty.adapter.out.repository.DongCodeJpaRepository;
import com.ssafy.realty.realty.adapter.out.repository.HouseInfoJpaRepository;
import com.ssafy.realty.realty.adapter.out.repository.HouseInfoMybatisRepository;
import com.ssafy.realty.realty.application.port.out.QueryRealtyPort;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.Markers;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;
import com.ssafy.realty.realty.domain.wrap.VicinityHomeInfos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class QueryRealtyPersistenceMybatisAdapter implements QueryRealtyPort {

    private final DongCodeJpaRepository dongCodeJpaRepository;
    private final HouseInfoMybatisRepository houseInfoMybatisRepository;
    private final HouseInfoJpaRepository houseInfoJpaRepository;
    private final CustomJpaRepository customJpaRepository;

    private final RealtyAdapterMapper realtyAdapterMapper;

    @Override
    public TotalVicinityHomeInfos queryMarkerVicinityHome(Marker marker) {
        List<VicinityHomeInfos> queryResult = new ArrayList<>();

        String dongCode = findDongCode(marker.getMarkerData().getAddress());
        Double lat = marker.getMarkerData().getLat();
        Double lng = marker.getMarkerData().getLng();
        List<Marker.MarkerData.MarkerFilter.Transportation> transportations =
                marker.getMarkerData().getFilter().getTransportations();

        for(Marker.MarkerData.MarkerFilter.Transportation t : transportations){
            Double distance = t.getType().getDistance(t.getTime());
            List<MarkerVicinityHomeInfo> infos =
                    houseInfoMybatisRepository.queryMarkerVicinityHome(dongCode, lat, lng, distance);

            queryResult.add(realtyAdapterMapper.mapToVicinityHomeInfos(infos, t.getType(), t.getTime()));
        }

        return realtyAdapterMapper.mapToTotalVicinityHomeInfos(queryResult);
    }

    @Override
    public DealInfos queryTotalHistory(String aptCode) {
        HouseInfoJpaEntity byAptCodeWithDeal = houseInfoJpaRepository.findByAptCodeWithDeals(aptCode);
        return realtyAdapterMapper.mapToDealInfos(byAptCodeWithDeal);
    }

    @Override
    public Markers queryCustomInfo(Long customId) {
        CustomJpaEntity customJpaEntity = customJpaRepository.findById(customId)
                .orElseThrow(() -> new NoSuchElementException("매물 글을 찾을 수 없습니다."));

        return realtyAdapterMapper.mapToMarkers(customJpaEntity);
    }

    private String findDongCode(String address){
        String[] ad = address.split(" ");

        if(ad.length >= 3){
            return dongCodeJpaRepository
                    .findBySidoNameAndGugunNameAndDongName(ad[0], ad[1], ad[2])
                    .getDongCode();
        }
        throw new NoSuchElementException("dongcode를 찾을 수 없습니다.");
    }
}
