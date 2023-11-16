package com.ssafy.realty.realty.application.port.in.command;

import com.ssafy.realty.realty.application.port.in.dto.UpdateDto;
import com.ssafy.realty.realty.domain.Update;

public interface UpdateRealtyUseCase {

    void update(UpdateDto updateDto);
}
