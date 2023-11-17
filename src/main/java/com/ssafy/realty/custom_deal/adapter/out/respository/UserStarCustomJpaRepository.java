package com.ssafy.realty.custom_deal.adapter.out.respository;

import com.ssafy.realty.custom_deal.adapter.out.entity.UserStarCustomJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStarCustomJpaRepository extends JpaRepository<UserStarCustomJpaEntity, Long> {

    Page<UserStarCustomJpaEntity> findByUserId(Long userId, Pageable pageable);
}
