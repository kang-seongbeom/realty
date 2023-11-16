package com.ssafy.realty.realty.application.port.in.command;

import com.ssafy.realty.realty.application.port.in.DeleteDto;

public interface DeleteRealtyUseCase {
    void delete(DeleteDto deleteDto);
}
