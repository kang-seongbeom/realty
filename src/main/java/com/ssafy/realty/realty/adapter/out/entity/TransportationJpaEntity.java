package com.ssafy.realty.realty.adapter.out.entity;

import com.ssafy.realty.common.TransportationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transportations")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransportationType type;

    @Column
    private Integer time;

    @ManyToOne
    @JoinColumn(name = "filters_id")
    private FilterJpaEntity filter;

    public void setFilter(FilterJpaEntity filter) {
        this.filter = filter;
    }
}
