package com.ssafy.realty.custom_deal.application.service;

import com.ssafy.realty.custom_deal.application.port.in.CommandCustomUseCase;
import com.ssafy.realty.custom_deal.application.port.in.dto.StarCustomDto;
import com.ssafy.realty.custom_deal.application.port.in.dto.ViewIncreaseDto;
import com.ssafy.realty.custom_deal.application.port.out.CommandCustomCustomPort;
import com.ssafy.realty.custom_deal.application.service.mapper.CustomServiceMapper;
import com.ssafy.realty.custom_deal.domain.StarCustom;
import com.ssafy.realty.custom_deal.domain.ViewIncrease;
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
    public void viewIncrease(ViewIncreaseDto viewIncreaseDto) {
        ViewIncrease viewIncrease = customServiceMapper.mapToViewIncrease(viewIncreaseDto);
        commandCustomPort.viewIncrease(viewIncrease);
    }

    @Override
    public void starIncrease(StarCustomDto starCustomDto) {
        StarCustom starCustom = customServiceMapper.mapToStarIncrease(starCustomDto);
        commandCustomPort.starCustom(starCustom);
    }
}
