package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "markers")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double lat;

    @Column
    private Double lng;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name = "customs_id")
    private CustomJpaEntity custom;

    @OneToOne(mappedBy = "marker", cascade = CascadeType.ALL, orphanRemoval = true)
    private FilterJpaEntity filter;

    public void setCustom(CustomJpaEntity custom) {
        this.custom = custom;
    }

    public void setFilter(FilterJpaEntity filter) {
        this.filter = filter;

        filter.setMarker(this);
    }
}
