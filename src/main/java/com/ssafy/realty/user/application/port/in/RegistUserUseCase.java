package com.ssafy.realty.user.application.port.in;

import com.ssafy.realty.user.application.port.in.dto.RegistDto;

public interface RegistUserUseCase {

    void regist(RegistDto registDto);
}
