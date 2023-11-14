package com.ssafy.realty.realty.application.service;

import com.ssafy.realty.realty.application.port.in.CommandRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.out.CommandRealtyPort;
import com.ssafy.realty.realty.application.service.mapper.RealtyServiceMapper;
import com.ssafy.realty.realty.domain.Save;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommandRealtyService implements CommandRealtyUseCase {

    private final RealtyServiceMapper realtyServiceMapper;

    private final CommandRealtyPort commandRealtyPort;

    @Transactional
    @Override
    public void save(SaveDto saveDto) {
        Save save = realtyServiceMapper.mapToSave(saveDto);
        commandRealtyPort.save(save);
    }
}
