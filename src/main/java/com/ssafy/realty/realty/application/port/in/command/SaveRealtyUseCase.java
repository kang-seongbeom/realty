package com.ssafy.realty.realty.application.port.in.command;

import com.ssafy.realty.realty.application.port.in.dto.SaveDto;

public interface SaveRealtyUseCase {

    void save(SaveDto saveDto);
}
