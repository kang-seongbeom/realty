package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "filters")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateLower")
    private LocalDate dateLower;

    @Column(name = "dateUpper")
    private LocalDate dateUpper;

    @Column(name = "dealAmountLower")
    private Long dealAmountLower;

    @Column(name = "dealAmountUpper")
    private Long dealAmountUpper;

    @Column(name = "areaLower")
    private Double areaLower;

    @Column(name = "areaUpper")
    private Double areaUpper;

    @OneToOne
    @JoinColumn(name = "markers_id")
    private MarkerJpaEntity marker;

    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationJpaEntity> transportations;

    public void setMarker(MarkerJpaEntity marker) {
        this.marker = marker;
    }

    public void setTransportations(List<TransportationJpaEntity> transportations) {
        this.transportations = transportations;

        for(TransportationJpaEntity transportation : transportations){
            transportation.setFilter(this);
        }
    }
}
