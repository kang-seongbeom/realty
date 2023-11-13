package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "houseinfo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HouseInfoJpaEntity {

    @Id
    @Column(name = "aptcode")
    String aptCode;

    @Column(name = "buildyear")
    Integer buildYear;

    @Column(name = "roadname")
    String roadName;

    @Column(name = "roadnamebonbun")
    String roadNameBonbun;

    @Column(name = "roadnamebubun")
    String roadNameBubun;

    @Column(name = "roadnameseq")
    String roadNameSeq;

    @Column(name = "roadnamebasementcode")
    String roadNameBasementCode;

    @Column
    String dong;

    @Column
    String bonbun;

    @Column
    String bubun;

    @Column(name = "sigungucode")
    String sigunguCode;

    @Column(name = "eubmyundongcode")
    String eubmyundongCode;

    @Column(name = "dongcode")
    String dongCode;

    @Column(name = "landcode")
    String landCode;

    @Column(name = "apartmentname")
    String apartmentName;

    @Column
    String jibun;

    @Column
    Double lng;

    @Column
    Double lat;

    @OneToMany(mappedBy = "houseinfo", fetch = FetchType.LAZY)
    private List<HouseDealJpaEntity> housedeals;

}
