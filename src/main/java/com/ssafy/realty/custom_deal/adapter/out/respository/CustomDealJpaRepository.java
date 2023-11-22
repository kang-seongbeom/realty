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

    @Query("SELECT c FROM  CustomDealJpaEntity c WHERE c.isTmp != 'tmp' ORDER BY c.id DESC")
    Page<CustomDealJpaEntity> findAllByOrderByIdDesc(Pageable pageable);

    @Query("SELECT c FROM  CustomDealJpaEntity c WHERE c.title = :title and c.isTmp != 'tmp' ORDER BY c.id DESC")
    Page<CustomDealJpaEntity> findByTitleContainingOrderByIdDesc(@Param("title") String title, Pageable pageable);

    @Query("SELECT c FROM CustomDealJpaEntity c JOIN c.user u WHERE u.nickname LIKE %:nickname% ORDER BY c.id DESC")
    Page<CustomDealJpaEntity> findByNicknameContaining(@Param("nickname") String nickname, Pageable pageable);

    Page<CustomDealJpaEntity> findByUserId(Long userId, Pageable pageable);
}
