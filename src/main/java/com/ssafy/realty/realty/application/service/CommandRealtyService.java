package com.ssafy.realty.realty.application.service;

import com.ssafy.realty.realty.application.port.in.CommandRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.DeleteDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveTemporaryDto;
import com.ssafy.realty.realty.application.port.in.dto.UpdateDto;
import com.ssafy.realty.realty.application.port.out.CommandRealtyPort;
import com.ssafy.realty.realty.application.service.mapper.RealtyServiceMapper;
import com.ssafy.realty.realty.domain.Delete;
import com.ssafy.realty.realty.domain.Save;
import com.ssafy.realty.realty.domain.SaveTemporary;
import com.ssafy.realty.realty.domain.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandRealtyService implements CommandRealtyUseCase {

    private final CommandRealtyPort commandRealtyPort;

    private final RealtyServiceMapper realtyServiceMapper;

    @Override
    public void save(SaveDto saveDto) {
        Save save = realtyServiceMapper.mapToSave(saveDto);
        commandRealtyPort.save(save);
    }

    @Override
    public void saveTemporary(SaveTemporaryDto saveTemporaryDto) {
        SaveTemporary saveTemporary = realtyServiceMapper.mapToSaveTemporary(saveTemporaryDto);
        commandRealtyPort.saveTemporary(saveTemporary);
    }

    @Override
    public void update(UpdateDto updateDto) {
        Update update = realtyServiceMapper.mapToUpdate(updateDto);
        commandRealtyPort.update(update);
    }

    @Override
    public void delete(DeleteDto deleteDto) {
        Delete delete = realtyServiceMapper.mapToDelete(deleteDto);
        commandRealtyPort.delete(delete);
    }
}
