package com.ssafy.realty.custom_deal.adapter.out.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
public class CustomUserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;
}
