package com.ssafy.realty.user.adapter.out;

import com.ssafy.realty.user.adapter.out.entity.UserJpaEntity;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final BCryptPasswordEncoder encoder;

    public UserJpaEntity mapToUserJpaEntity(UserDomain userDomain){
        return UserJpaEntity
                .builder()
                .username(userDomain.getUserDomainData().getUsername())
                .password(encoder.encode(userDomain.getUserDomainData().getPassword()))
                .nickname(userDomain.getUserDomainData().getNickname())
                .role(userDomain.getUserDomainData().getRole())
                .build();
    }

    public UserDomain mapToUserDomain(UserJpaEntity userJpaEntity){
        return UserDomain.init(
                userJpaEntity.getId(),
                userJpaEntity.getUsername(),
                userJpaEntity.getPassword(),
                userJpaEntity.getNickname());
    }
}
