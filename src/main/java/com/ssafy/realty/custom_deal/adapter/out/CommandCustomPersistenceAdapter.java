package com.ssafy.realty.custom_deal.adapter.out;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.mapper.CustomAdapterMapper;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomDealJpaRepository;
import com.ssafy.realty.custom_deal.application.port.out.CommandCustomPort;
import com.ssafy.realty.custom_deal.domain.ViewIncrease;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CommandCustomPersistenceAdapter implements CommandCustomPort {

    private final CustomDealJpaRepository customDealJpaRepository;

    @Override
    public void viewIncrease(ViewIncrease viewIncrease) {
        CustomDealJpaEntity customDealJpa = findById(viewIncrease);
        customDealJpa.viewIncrease();
    }

    private CustomDealJpaEntity findById(ViewIncrease viewIncrease) {
        return customDealJpaRepository.findById(viewIncrease.getViewIncreaseCustomId().getValue())
                .orElseThrow(() -> new NoSuchElementException("매물 정보 글을 찾을 수 없습니다."));
    }
}
