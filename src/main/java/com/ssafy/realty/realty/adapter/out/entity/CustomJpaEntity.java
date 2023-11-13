package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customs")
@Getter
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
    private UserJpaEntity user;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarkerJpaEntity> markers;

}
