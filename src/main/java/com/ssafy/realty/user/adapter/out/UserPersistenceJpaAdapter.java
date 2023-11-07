package com.ssafy.realty.user.adapter.out;

import com.ssafy.realty.user.adapter.out.entity.UserJpaEntity;
import com.ssafy.realty.user.adapter.out.repository.UserJpaRepository;
import com.ssafy.realty.user.application.port.out.DeleteUserPort;
import com.ssafy.realty.user.application.port.out.RegistUserPort;
import com.ssafy.realty.user.application.port.out.UpdateUserPort;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceJpaAdapter implements RegistUserPort, DeleteUserPort, UpdateUserPort {

    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void regist(UserDomain userDomain) {
        UserJpaEntity entity = userMapper.mapToUserJpaEntity(userDomain);
        userJpaRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public void update(UserDomain userDomain) {
        UserJpaEntity entity = userJpaRepository.findById(userDomain.getUserDomainId().getValue())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        entity.toUpdate(userDomain);
    }
}
