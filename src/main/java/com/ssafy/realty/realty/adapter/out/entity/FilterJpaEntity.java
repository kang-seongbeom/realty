package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "filters")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilterJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateLower")
    private String dateLower;

    @Column(name = "dateUpper")
    private String dateUpper;

    @Column(name = "dealAmountLower")
    private Integer dealAmountLower;

    @Column(name = "dealAmountUpper")
    private Integer dealAmountUpper;

    @Column(name = "areaLower")
    private Double areaLower;

    @Column(name = "areaUpper")
    private Double areaUpper;

    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationJpaEntity> transportations;
}
