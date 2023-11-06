package com.ssafy.realty.user.adapter.out.repository;

import com.ssafy.realty.user.adapter.out.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
}
