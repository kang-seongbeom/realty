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
    String dongCode;

    @Column
    String sidoName;

    @Column
    String gugunName;

    @Column
    String dongName;
}
