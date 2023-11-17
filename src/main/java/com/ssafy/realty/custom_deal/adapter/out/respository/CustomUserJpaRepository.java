package com.ssafy.realty.custom_deal.adapter.out.respository;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserJpaRepository extends JpaRepository<CustomUserJpaEntity, Long> {
}
