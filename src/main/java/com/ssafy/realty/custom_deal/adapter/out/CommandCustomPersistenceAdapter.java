package com.ssafy.realty.custom_deal.adapter.out;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.entity.CustomUserJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.entity.UserStarCustomJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.mapper.CustomAdapterMapper;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomDealJpaRepository;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomUserJpaRepository;
import com.ssafy.realty.custom_deal.adapter.out.respository.UserStarCustomJpaRepository;
import com.ssafy.realty.custom_deal.application.port.out.CommandCustomCustomPort;
import com.ssafy.realty.custom_deal.domain.StarCustom;
import com.ssafy.realty.custom_deal.domain.ViewIncrease;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CommandCustomPersistenceAdapter implements CommandCustomCustomPort {

    private final CustomDealJpaRepository customDealJpaRepository;
    private final CustomUserJpaRepository customUserJpaRepository;
    private final UserStarCustomJpaRepository userStarCustomJpaRepository;

    private final CustomAdapterMapper adapterMapper;

    @Override
    public void starCustom(StarCustom starCustom) {
        CustomUserJpaEntity user = findUserByUserId(starCustom.getStarCustomUserId().getValue());
        CustomDealJpaEntity custom = findCustomByCustomId(starCustom.getStarCustomId().getValue());

        if(user.isStarCustom(custom.getId())){
            Long mappingId = userStarCustomJpaRepository.findByUserIdAndCustomId(user.getId(), custom.getId()).getId();
            userStarCustomJpaRepository.deleteById(mappingId);
        }else {
            UserStarCustomJpaEntity userStarCustomJpaEntity = adapterMapper.mapToUserStarCustomJpaEntity(user, custom);
            userStarCustomJpaRepository.save(userStarCustomJpaEntity);
        }
    }

    private CustomUserJpaEntity findUserByUserId(Long userId) {
        return customUserJpaRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
    }

    private CustomDealJpaEntity findCustomByCustomId(Long customId) {
        return customDealJpaRepository.findById(customId)
                .orElseThrow(() -> new NoSuchElementException("매물 정보 글을 찾을 수 없습니다."));
    }

}
