package com.ssafy.realty.user.adapter.out.entity;

import com.ssafy.realty.common.Role;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "nickname")
    String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void toUpdate(UserDomain updateWantUser){
        this.password = updateWantUser.getUserDomainData().getPassword();
        this.nickname = updateWantUser.getUserDomainData().getNickname();
    }
}
