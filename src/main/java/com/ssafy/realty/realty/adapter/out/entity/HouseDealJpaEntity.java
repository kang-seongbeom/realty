package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "housedeal")
@AllArgsConstructor
@NoArgsConstructor
public class HouseDealJpaEntity {

    @Id
    String no;

    @Column
    String dealAmount;

    @Column
    Integer dealYear;

    @Column
    Integer dealMonth;

    @Column
    Integer dealDay;

    @Column
    Double area;

    @Column
    Integer floor;

    @Column(name = "aptCode")
    Long aptCode;

    @ManyToOne
    @JoinColumn(name = "aptCode", insertable = false, updatable = false)
    private HouseInfoJpaEntity houseinfo;
}
