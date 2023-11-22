package com.ssafy.realty.custom_deal.adapter.out.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.NoSuchElementException;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
public class CustomUserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "user")
    private Set<UserStarCustomJpaEntity> startToCustoms;

    public boolean isStarCustom(long customId){
        return startToCustoms
                .stream()
                .anyMatch(c -> c.getCustom().getId() == customId);
    }
}
