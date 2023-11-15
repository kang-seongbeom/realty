package com.ssafy.realty.realty.application.service;

import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import com.ssafy.realty.realty.application.port.in.CommandRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveTemporaryDto;
import com.ssafy.realty.realty.application.port.out.CommandRealtyPort;
import com.ssafy.realty.realty.application.service.mapper.RealtyServiceMapper;
import com.ssafy.realty.realty.domain.Save;
import com.ssafy.realty.realty.domain.SaveTemporary;
import com.ssafy.realty.realty.domain.wrap.Markers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommandRealtyService implements CommandRealtyUseCase {

    private final RealtyServiceMapper realtyServiceMapper;

    private final CommandRealtyPort commandRealtyPort;

    @Transactional
    @Override
    public void save(SaveDto saveDto) {
        Save save = realtyServiceMapper.mapToSave(saveDto);
        commandRealtyPort.save(save);
    }

    @Transactional
    @Override
    public void saveTemporary(SaveTemporaryDto saveTemporaryDto) {
        SaveTemporary saveTemporary = realtyServiceMapper.mapToSaveTemporary(saveTemporaryDto);
        commandRealtyPort.saveTemporary(saveTemporary);
    }
}
