package com.ssafy.realty.realty.adapter.out.repository;

import com.ssafy.realty.realty.adapter.out.entity.HouseDealJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.HouseInfoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseInfoJpaRepository extends JpaRepository<HouseInfoJpaEntity, Long> {

    @Query("select hi from HouseInfoJpaEntity  hi join fetch hi.housedeals hd where hi.aptCode = :aptCode")
    HouseInfoJpaEntity findByAptCodeWithDeals(@Param("aptCode") String aptCode);
}
