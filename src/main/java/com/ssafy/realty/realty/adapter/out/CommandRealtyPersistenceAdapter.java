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
import com.ssafy.realty.realty.domain.Update;
import com.ssafy.realty.realty.domain.wrap.Markers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

        deleteBeforeSaveTemporary(user.getId());

        TemporaryCustomJpaEntity temporaryCustomJpaEntity =
                realtyAdapterMapper.mapToTemporaryJpaEntity(saveTemporary);

        user.addCustom(temporaryCustomJpaEntity);
        customJpaRepository.save(temporaryCustomJpaEntity);
    }

    @Override
    public void update(Update update) {
        CustomJpaEntity customJpa = customJpaRepository.findById(update.getUpdateCustomId().getCustomId())
                .orElseThrow(() -> new NoSuchElementException("커스텀 글을 찾을 수 없습니다."));

        if((long)customJpa.getUser().getId() != update.getUpdateUserId().getUserId()){
            throw new SecurityException("작성자가 아닌 글을 수정할 수 없습니다.");
        }

        customJpa.updateTitle(update.getUpdateData().getTitle());
        customJpa.updateMarkers(realtyAdapterMapper.mapToMarkerJpaEntities(update.getUpdateData().getMarkers()));
    }

    private RealtyUserJpaEntity findByUserId(Long userId){
        return realtyUserJpaRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
    }

    public void deleteBeforeSaveTemporary(Long userId){
        CustomJpaEntity beforeSavedTmp = customJpaRepository.findByUserId(userId);
        if(beforeSavedTmp != null){
            customJpaRepository.deleteById(beforeSavedTmp.getId());
        }
    }
}
