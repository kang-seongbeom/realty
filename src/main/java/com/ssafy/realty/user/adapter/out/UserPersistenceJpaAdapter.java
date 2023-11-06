package com.ssafy.realty.user.adapter.out;

import com.ssafy.realty.user.adapter.out.entity.UserJpaEntity;
import com.ssafy.realty.user.adapter.out.repository.UserJpaRepository;
import com.ssafy.realty.user.application.port.out.DeleteUserPort;
import com.ssafy.realty.user.application.port.out.RegistUserPort;
import com.ssafy.realty.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceJpaAdapter implements RegistUserPort, DeleteUserPort {

    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void regist(UserDomain userDomain) {
        UserJpaEntity entity = userMapper.mapToUserJpaEntity(userDomain);
        userJpaRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }
}
