package com.ssafy.realty.realty.adapter.out.repository;

import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomJpaRepository extends JpaRepository<CustomJpaEntity, Long> {
}
