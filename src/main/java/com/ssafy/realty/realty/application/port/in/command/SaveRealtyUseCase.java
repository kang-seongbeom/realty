package com.ssafy.realty.realty.application.port.in.command;

import com.ssafy.realty.realty.application.port.in.dto.SaveDto;
import com.ssafy.realty.realty.application.port.in.dto.SaveTemporaryDto;
import com.ssafy.realty.realty.application.port.in.dto.UpdateDto;

public interface SaveRealtyUseCase {
    void save(SaveDto saveDto);

    void saveTemporary(SaveTemporaryDto saveTemporaryDto);
}
