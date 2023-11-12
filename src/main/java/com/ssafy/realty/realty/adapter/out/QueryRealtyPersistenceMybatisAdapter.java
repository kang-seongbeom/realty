package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.adapter.out.mybatis.MarkerVicinityHomeInfoMapper;
import com.ssafy.realty.realty.adapter.out.repository.DongCodeJpaRepository;
import com.ssafy.realty.realty.adapter.out.repository.HouseInfoMybatisRepository;
import com.ssafy.realty.realty.application.port.out.QueryRealtyPort;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class QueryRealtyPersistenceMybatisAdapter implements QueryRealtyPort {

    private final DongCodeJpaRepository dongCodeJpaRepository;
    private final HouseInfoMybatisRepository houseInfoMybatisRepository;

    @Override
    public DealInfos queryMarkerVicinityHome(Marker marker) {
        List<List<MarkerVicinityHomeInfoMapper>> result = new ArrayList<>();

        String dongCode = findDongCode(marker.getMarkerData().getAddress());
        Double lat = marker.getMarkerData().getLat();
        Double lng = marker.getMarkerData().getLng();
        List<Marker.MarkerData.MarkerFilter.Transportation> transportations =
                marker.getMarkerData().getFilter().getTransportations();

        for(Marker.MarkerData.MarkerFilter.Transportation t : transportations){
            Double distance = t.getType().getDistance(t.getTime());
            result.add(houseInfoMybatisRepository.queryMarkerVicinityHome(dongCode, lat, lng, distance));
        }

        return null;
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
