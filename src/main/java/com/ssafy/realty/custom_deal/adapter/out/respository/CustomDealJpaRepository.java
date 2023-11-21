package com.ssafy.realty.custom_deal.adapter.out.respository;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.entity.CustomUserJpaEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomDealJpaRepository extends JpaRepository<CustomDealJpaEntity, Long> {

    Page<CustomDealJpaEntity> findAllByOrderByIdDesc(Pageable pageable);

    Page<CustomDealJpaEntity> findByTitleContainingOrderByIdDesc(String title, Pageable pageable);

    @Query("SELECT c FROM CustomDealJpaEntity c JOIN c.user u WHERE u.nickname LIKE %:nickname%")
    Page<CustomDealJpaEntity> findByNicknameContaining(@Param("nickname") String nickname, Pageable pageable);

    Page<CustomDealJpaEntity> findByUserId(Long userId, Pageable pageable);
}
