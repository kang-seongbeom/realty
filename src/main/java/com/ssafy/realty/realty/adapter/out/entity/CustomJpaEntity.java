package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customs")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private RealtyUserJpaEntity user;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarkerJpaEntity> markers;

    public void setUser(RealtyUserJpaEntity user) {
        this.user = user;
    }

    public void setMarkers(List<MarkerJpaEntity> markers) {
        this.markers = markers;

        for(MarkerJpaEntity marker : markers){
            marker.setCustom(this);
        }
    }
}
