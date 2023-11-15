package com.ssafy.realty.custom_deal.adapter.out;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.mapper.CustomAdapterMapper;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomDealJpaRepository;
import com.ssafy.realty.custom_deal.application.port.out.QueryCustomPort;
import com.ssafy.realty.custom_deal.domain.IsOwner;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.repository.CustomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class QueryCustomPersistenceAdapter implements QueryCustomPort {

    private final CustomJpaRepository customJpaRepository;
    private final CustomDealJpaRepository customDealJpaRepository;

    private final CustomAdapterMapper customAdapterMapper;

    @Override
    public Summaries total() {
        List<CustomDealJpaEntity> total = customDealJpaRepository.findAll();
        return customAdapterMapper.mapToSummaries(total);
    }

    @Override
    public Summaries myCustomInfos(Long userId) {
        List<CustomDealJpaEntity> infos = customDealJpaRepository.findByUserId(userId);
        return customAdapterMapper.mapToSummaries(infos);
    }

    @Override
    public boolean isOwner(IsOwner isOwner) {
        CustomJpaEntity custom = customJpaRepository.findById(isOwner.getIsOwnerCustomId().getValue())
                .orElseThrow(() -> new NoSuchElementException("해당 커스텀을 찾을 수 없습니다."));

        return (long) custom.getUser().getId() == isOwner.getIsOwnerUserId().getValue();
    }
}
