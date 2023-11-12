package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dongcode")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DongCodeJpaEntity {

    @Id
    @Column(name = "dongcode")
    String dongCode;

    @Column(name = "sidoname")
    String sidoName;

    @Column(name = "gugunname")
    String gugunName;

    @Column(name = "dongname")
    String dongName;
}
