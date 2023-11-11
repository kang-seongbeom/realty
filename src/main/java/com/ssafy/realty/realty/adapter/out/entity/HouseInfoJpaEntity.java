package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "houseinfo")
@AllArgsConstructor
@NoArgsConstructor
public class HouseInfoJpaEntity {

    @Id
    String aptCode;

    @Column
    Integer buildYear;

    @Column
    String roadName;

    @Column
    String roadNameBonbun;

    @Column
    String roadNameBubun;

    @Column
    String roadNameSeq;

    @Column
    String roadNameBasementCode;

    @Column
    String dong;

    @Column
    String bonbun;

    @Column
    String bubun;

    @Column
    String sigunguCode;

    @Column
    String eubmyundongCode;

    @Column
    String dongCode;

    @Column
    String landCode;

    @Column
    String apartmentName;

    @Column
    String jibun;

    @Column
    Double lng;

    @Column
    Double lat;

    @OneToMany(mappedBy = "houseinfo")
    private List<HouseDealJpaEntity> housedeals;

}
