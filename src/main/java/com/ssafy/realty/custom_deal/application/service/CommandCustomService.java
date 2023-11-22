package com.ssafy.realty.custom_deal.application.service;

import com.ssafy.realty.custom_deal.application.port.in.CommandCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.in.dto.StarCustomDto;
import com.ssafy.realty.custom_deal.application.port.out.CommandCustomCustomPort;
import com.ssafy.realty.custom_deal.application.service.mapper.CustomServiceMapper;
import com.ssafy.realty.custom_deal.domain.StarCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandCustomService implements CommandCustomUseCase {

    private final CommandCustomCustomPort commandCustomPort;

    private final CustomServiceMapper customServiceMapper;

    @Override
    public void starIncrease(StarCustomDto starCustomDto) {
        StarCustom starCustom = customServiceMapper.mapToStarIncrease(starCustomDto);
        commandCustomPort.starCustom(starCustom);
    }
}
