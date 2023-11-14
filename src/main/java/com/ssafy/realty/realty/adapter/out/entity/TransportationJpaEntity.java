package com.ssafy.realty.realty.adapter.out.entity;

import com.ssafy.realty.common.TransportationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "createDate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "filters_id")
    private FilterJpaEntity filter;

    public void setFilter(FilterJpaEntity filter) {
        this.filter = filter;
    }
}
