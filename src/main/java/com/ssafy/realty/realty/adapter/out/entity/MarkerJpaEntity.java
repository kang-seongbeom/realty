package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "createDate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

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
