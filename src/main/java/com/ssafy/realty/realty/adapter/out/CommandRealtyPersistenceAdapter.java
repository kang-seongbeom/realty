package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.RealtyUserJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.TemporaryCustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.mapper.RealtyAdapterMapper;
import com.ssafy.realty.realty.adapter.out.repository.CustomJpaRepository;
import com.ssafy.realty.realty.adapter.out.repository.RealtyUserJpaRepository;
import com.ssafy.realty.realty.application.port.out.CommandRealtyPort;
import com.ssafy.realty.realty.domain.Save;
import com.ssafy.realty.realty.domain.SaveTemporary;
import com.ssafy.realty.realty.domain.wrap.Markers;
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
        RealtyUserJpaEntity user = findByUserId(save.getSaveUserId().getUserId());

        CustomJpaEntity customJpaEntity = realtyAdapterMapper.mapToCustomJpaEntity(save);
        user.addCustom(customJpaEntity);
        customJpaRepository.save(customJpaEntity);
    }

    @Override
    public void saveTemporary(SaveTemporary saveTemporary) {
        RealtyUserJpaEntity user = findByUserId(saveTemporary.getSaveUserId().getUserId());

        TemporaryCustomJpaEntity temporaryCustomJpaEntity =
                realtyAdapterMapper.mapToTemporaryJpaEntity(saveTemporary);

        user.addCustom(temporaryCustomJpaEntity);
        customJpaRepository.save(temporaryCustomJpaEntity);
    }

    private RealtyUserJpaEntity findByUserId(Long userId){
        return realtyUserJpaRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
    }
}
