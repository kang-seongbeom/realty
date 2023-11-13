package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "housedeal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HouseDealJpaEntity {

    @Id
    String no;

    @Column(name = "dealamount")
    String dealAmount;

    @Column(name = "dealyear")
    Integer dealYear;

    @Column(name = "dealmonth")
    Integer dealMonth;

    @Column(name = "dealday")
    Integer dealDay;

    @Column
    Double area;

    @Column
    Integer floor;

    @Column(name = "aptcode")
    Long aptCode;

    @ManyToOne
    @JoinColumn(name = "aptcode", insertable = false, updatable = false)
    private HouseInfoJpaEntity houseinfo;
}
