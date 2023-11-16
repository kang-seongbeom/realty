package com.ssafy.realty.custom_deal.adapter.out.respository;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomDealJpaRepository extends JpaRepository<CustomDealJpaEntity, Long> {

    Page<CustomDealJpaEntity> findAllByOrderByIdDesc(Pageable pageable);

    Page<CustomDealJpaEntity> findByUserId(Long userId, Pageable pageable);
}
