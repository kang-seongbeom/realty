package com.ssafy.realty.custom_deal.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserStarCustomJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private CustomUserJpaEntity user;

    @ManyToOne
    @JoinColumn(name = "customs_id")
    private CustomDealJpaEntity custom;
}
