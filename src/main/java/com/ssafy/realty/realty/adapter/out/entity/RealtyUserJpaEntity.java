package com.ssafy.realty.realty.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RealtyUserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomJpaEntity> customs;

    public void addCustom(CustomJpaEntity custom) {
        if(customs == null) {
            customs = new ArrayList<>();
        }
        customs.add(custom);

        custom.setUser(this);
    }
}
