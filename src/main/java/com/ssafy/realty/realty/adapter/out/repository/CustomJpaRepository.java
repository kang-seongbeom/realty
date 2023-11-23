package com.ssafy.realty.realty.adapter.out.repository;

import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomJpaRepository extends JpaRepository<CustomJpaEntity, Long> {


    @Query(value = "select count(*) from customs where users_id = :userId and is_tmp = 'tmp'", nativeQuery = true)
    Integer countByUserIdAndIsTmpTrue(@Param("userId") Long userId);

    @Query("select c from CustomJpaEntity  c where c.user.id = :userId")
    CustomJpaEntity findByUserId(@Param("userId") Long userId);

    @Query(value = "select * from customs where users_id = :userId and is_tmp = 'tmp'", nativeQuery = true)
    CustomJpaEntity findTmpByUserId(@Param("userId") Long userId);
}
