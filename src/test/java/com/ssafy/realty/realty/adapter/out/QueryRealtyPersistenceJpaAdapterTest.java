package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.wrap.DealInfos;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryRealtyPersistenceJpaAdapterTest {

    @Autowired
    private QueryRealtyPersistenceAdapter queryRealtyPersistenceAdapter;

    @Test
    @DisplayName("마커 주변 집 정보")
    public void findVicinity(){
        List<String[]> transportations = new ArrayList<>();
        transportations.add(new String[]{"walk", "5"});

        Marker marker = Marker.init(37.4035784558851, 126.90744511351,
                "경기도 안양시 만안구 박달동",
                null, null,
                null, null,
                null, null,
                transportations);
        TotalVicinityHomeInfos totalVicinityHomeInfos =
                queryRealtyPersistenceAdapter.queryMarkerVicinityHome(marker);

        assertFalse(totalVicinityHomeInfos.getTotal().isEmpty());
    }

    @Test
    @DisplayName("한 집의 전체 거래 정보")
    public void historyAHouse() {
        // given
        String aptCode = "11110000000003";

        // when
        DealInfos dealInfos = queryRealtyPersistenceAdapter.queryTotalHistory(aptCode);

        // then
        assertFalse(dealInfos.getDealInfos().isEmpty());
    }
}
