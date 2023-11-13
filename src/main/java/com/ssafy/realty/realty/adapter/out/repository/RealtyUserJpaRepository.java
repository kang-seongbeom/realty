package com.ssafy.realty.realty.adapter.out.repository;

import com.ssafy.realty.realty.adapter.out.entity.RealtyUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtyUserJpaRepository extends JpaRepository<RealtyUserJpaEntity, Long> {
}
