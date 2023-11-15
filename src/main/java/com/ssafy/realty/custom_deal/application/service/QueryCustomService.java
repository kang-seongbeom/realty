package com.ssafy.realty.custom_deal.application.service;

import com.ssafy.realty.custom_deal.application.port.in.IsOwnerDto;
import com.ssafy.realty.custom_deal.application.port.in.QueryCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.out.QueryCustomPort;
import com.ssafy.realty.custom_deal.application.port.out.dto.wrap.CustomSummaryDtos;
import com.ssafy.realty.custom_deal.application.service.mapper.CustomServiceMapper;
import com.ssafy.realty.custom_deal.domain.IsOwner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryCustomService implements QueryCustomUseCase {

    private final QueryCustomPort queryCustomPort;

    private final CustomServiceMapper customServiceMapper;

    @Override
    public CustomSummaryDtos total() {
        return customServiceMapper.mapToCustomSummaryDtos(queryCustomPort.total());
    }

    @Override
    public CustomSummaryDtos myCustomInfos(Long userId) {
        return customServiceMapper.mapToCustomSummaryDtos(queryCustomPort.myCustomInfos(userId));
    }

    @Override
    public boolean isOwner(IsOwnerDto isOwnerDto) {
        IsOwner isOwner = customServiceMapper.mapToIsOwner(isOwnerDto);
        return queryCustomPort.isOwner(isOwner);
    }
}
