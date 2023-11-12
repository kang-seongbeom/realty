package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.domain.Marker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class QueryRealtyPersistenceJpaAdapterTest {

    @Autowired
    private QueryRealtyPersistenceMybatisAdapter queryRealtyPersistenceMybatisAdapter;

    @Test
    public void findVicinity(){
        List<String[]> transportations = new ArrayList<>();
        transportations.add(new String[]{"walk", "5"});

        Marker marker = Marker.init(35.1211126355306, 129.043881421296,
                "부산광역시 동구 초량동",
                null, null,
                null, null,
                null, null,
                transportations);
        queryRealtyPersistenceMybatisAdapter.queryMarkerVicinityHome(marker);
    }
}
