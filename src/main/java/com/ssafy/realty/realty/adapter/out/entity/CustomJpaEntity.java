package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customs")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "is_tmp", discriminatorType = DiscriminatorType.STRING)
public class CustomJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer view;

    @Column
    private Integer star;

    @Column(name = "createDate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private RealtyUserJpaEntity user;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MarkerJpaEntity> markers;

    public void setUser(RealtyUserJpaEntity user) {
        this.user = user;
    }

    public void setMarkers(List<MarkerJpaEntity> markers) {
        this.markers = markers;

        for (MarkerJpaEntity marker : markers) {
            marker.setCustom(this);
        }
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateMarkers(List<MarkerJpaEntity> markers){
        this.markers = markers;
    }

    public void increaseView(){
        this.view += 1;
    }
}
