package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.RealtyUserJpaEntity;
import com.ssafy.realty.realty.adapter.out.mapper.RealtyAdapterMapper;
import com.ssafy.realty.realty.adapter.out.repository.CustomJpaRepository;
import com.ssafy.realty.realty.adapter.out.repository.RealtyUserJpaRepository;
import com.ssafy.realty.realty.application.port.out.CommandRealtyPort;
import com.ssafy.realty.realty.domain.Save;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CommandRealtyPersistenceAdapter implements CommandRealtyPort {

    private final RealtyAdapterMapper realtyAdapterMapper;

    private final CustomJpaRepository customJpaRepository;
    private final RealtyUserJpaRepository realtyUserJpaRepository;

    @Override
    public void save(Save save) {
        RealtyUserJpaEntity user = realtyUserJpaRepository.findById(save.getSaveUserId().getUserId())
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));

        CustomJpaEntity customJpaEntity = realtyAdapterMapper.mapToCustomJpaEntity(save);
        user.addCustom(customJpaEntity);
        customJpaRepository.save(customJpaEntity);
    }
}
