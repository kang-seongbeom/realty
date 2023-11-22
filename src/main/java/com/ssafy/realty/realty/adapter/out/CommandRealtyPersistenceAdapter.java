package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.RealtyUserJpaEntity;
import com.ssafy.realty.realty.adapter.out.entity.TemporaryCustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.mapper.RealtyAdapterMapper;
import com.ssafy.realty.realty.adapter.out.repository.CustomJpaRepository;
import com.ssafy.realty.realty.adapter.out.repository.RealtyUserJpaRepository;
import com.ssafy.realty.realty.application.port.out.CommandRealtyPort;
import com.ssafy.realty.realty.domain.*;
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
        RealtyUserJpaEntity user = customFindByUserId(save.getSaveUserId().getUserId());

        CustomJpaEntity customJpaEntity = realtyAdapterMapper.mapToCustomJpaEntity(save);
        user.addCustom(customJpaEntity);
        customJpaRepository.save(customJpaEntity);
    }

    @Override
    public void saveTemporary(SaveTemporary saveTemporary) {
        RealtyUserJpaEntity user = customFindByUserId(saveTemporary.getSaveUserId().getUserId());

        deleteBeforeSaveTemporary(user.getId());

        TemporaryCustomJpaEntity temporaryCustomJpaEntity =
                realtyAdapterMapper.mapToTemporaryJpaEntity(saveTemporary);

        user.addCustom(temporaryCustomJpaEntity);
        customJpaRepository.save(temporaryCustomJpaEntity);
    }

    @Override
    public void update(Update update) {
        CustomJpaEntity customJpa = customFindById(update.getUpdateCustomId().getValue());

        notPermitIfNotOwner(update.getUpdateUserId().getValue(), customJpa.getUser().getId());

        customJpa.updateTitle(update.getUpdateData().getTitle());
        customJpa.updateMarkers(realtyAdapterMapper.mapToMarkerJpaEntities(update.getUpdateData().getMarkers()));
    }

    @Override
    public void delete(Delete delete) {
        CustomJpaEntity customJpa = customFindById(delete.getCustomId().getValue());

        notPermitIfNotOwner(delete.getUserId().getValue(), customJpa.getUser().getId());

        customJpaRepository.deleteById(customJpa.getId());
    }

    @Override
    public void viewIncrease(ViewIncrease viewIncrease) {
        CustomJpaEntity customJpa = customFindById(viewIncrease.getViewIncreaseCustomId().getValue());
        customJpa.increaseView();
    }

    private CustomJpaEntity customFindById(Long customId) {
        return customJpaRepository.findById(customId)
                .orElseThrow(() -> new NoSuchElementException("커스텀 글을 찾을 수 없습니다."));
    }

    private RealtyUserJpaEntity customFindByUserId(Long userId){
        return realtyUserJpaRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
    }

    private static void notPermitIfNotOwner(long userId, long customOwnerId) {
        if(userId != customOwnerId){
            throw new SecurityException("작성자가 아닌 글을 조작할 수 없습니다.");
        }
    }

    public void deleteBeforeSaveTemporary(Long userId){
        CustomJpaEntity beforeSavedTmp = customJpaRepository.findByUserId(userId);
        if(beforeSavedTmp != null){
            customJpaRepository.deleteById(beforeSavedTmp.getId());
        }
    }
}
