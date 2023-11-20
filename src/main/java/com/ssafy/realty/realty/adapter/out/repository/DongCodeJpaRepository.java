package com.ssafy.realty.realty.adapter.out.repository;

import com.ssafy.realty.realty.adapter.out.entity.DongCodeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DongCodeJpaRepository extends JpaRepository<DongCodeJpaEntity, String> {

    Optional<DongCodeJpaEntity> findBySidoNameAndGugunNameAndDongName(String sidoName, String gugunName, String dongName);
}
