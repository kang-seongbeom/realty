package com.ssafy.realty.custom_deal.adapter.out;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.mapper.CustomAdapterMapper;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomDealJpaRepository;
import com.ssafy.realty.custom_deal.application.port.out.QueryCustomPort;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class QueryCustomPersistenceAdapter implements QueryCustomPort {

    private final CustomDealJpaRepository customDealJpaRepository;

    private final CustomAdapterMapper customAdapterMapper;

    @Override
    public Summaries total() {
        List<CustomDealJpaEntity> total = customDealJpaRepository.findAll();
        return customAdapterMapper.mapToSummaries(total);
    }
}
