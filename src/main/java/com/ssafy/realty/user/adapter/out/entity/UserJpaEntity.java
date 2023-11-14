package com.ssafy.realty.user.adapter.out.entity;

import com.ssafy.realty.common.Role;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
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

    @Column(name = "createDate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    public void toUpdate(UserDomain updateWantUser){
        this.password = updateWantUser.getUserDomainData().getPassword();
        this.nickname = updateWantUser.getUserDomainData().getNickname();
    }
}
