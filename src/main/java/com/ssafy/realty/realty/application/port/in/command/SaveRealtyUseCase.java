package com.ssafy.realty.realty.application.port.in.command;

import com.ssafy.realty.realty.application.port.common_dto.wrap.MarkerDtos;
import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveTemporaryDto;

public interface SaveRealtyUseCase {
    void save(SaveDto saveDto);

    void saveTemporary(SaveTemporaryDto saveTemporaryDto);
}
