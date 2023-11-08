package com.ssafy.realty.user.application.port.in.command;

import com.ssafy.realty.user.application.port.in.dto.UpdateDto;

public interface UpdateUserUseCase {
    void update(UpdateDto updateDto);

}
