package com.ssafy.realty.realty.adapter.out.repository;

import com.ssafy.realty.realty.adapter.out.entity.DongCodeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DongCodeJpaRepository extends JpaRepository<DongCodeJpaEntity, String> {

    DongCodeJpaEntity findBySidoNameAndGugunNameAndDongName(String sidoName, String gugunName, String dongName);
}
