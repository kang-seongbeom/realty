package com.ssafy.realty.realty.adapter.out.repository;

import com.ssafy.realty.realty.adapter.out.mybatis.MarkerVicinityHomeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HouseInfoMybatisRepository {

    List<MarkerVicinityHomeInfo> queryMarkerVicinityHome(
            @Param("dongCode") String dongCode,
            @Param("baseLatitude") Double baseLatitude,
            @Param("baseLongitude") Double baseLongitude,
            @Param("distance") Double distance
    );
}
