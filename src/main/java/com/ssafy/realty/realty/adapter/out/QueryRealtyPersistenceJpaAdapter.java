package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.adapter.out.repository.DongCodeJpaRepository;
import com.ssafy.realty.realty.application.port.out.QueryRealtyPort;
import com.ssafy.realty.realty.domain.DealInfo;
import com.ssafy.realty.realty.domain.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class QueryRealtyPersistenceJpaAdapter implements QueryRealtyPort {

    private final DongCodeJpaRepository dongCodeJpaRepository;

    @Override
    public List<DealInfo> queryMarkerVicinityHome(Marker marker) {
        String dongCode = findDongCode(marker.getMarkerData().getAddress());
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
