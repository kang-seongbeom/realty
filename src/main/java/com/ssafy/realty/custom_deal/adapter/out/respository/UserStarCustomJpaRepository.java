package com.ssafy.realty.custom_deal.adapter.out.respository;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.entity.UserStarCustomJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserStarCustomJpaRepository extends JpaRepository<UserStarCustomJpaEntity, Long> {

    Page<UserStarCustomJpaEntity> findByUserId(Long userId, Pageable pageable);

    @Query(value = "SELECT uc.custom FROM UserStarCustomJpaEntity uc WHERE uc.user.id = :userId",
            countQuery = "SELECT COUNT(uc) FROM UserStarCustomJpaEntity uc WHERE uc.user.id = :userId")
    Page<CustomDealJpaEntity> findCustomByUserId(Long userId, Pageable pageable);
}
