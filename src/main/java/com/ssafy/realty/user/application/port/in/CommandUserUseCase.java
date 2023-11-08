package com.ssafy.realty.user.application.port.in;

import com.ssafy.realty.user.application.port.in.command.DeleteUserUseCase;
import com.ssafy.realty.user.application.port.in.command.RegistUserUseCase;
import com.ssafy.realty.user.application.port.in.command.UpdateUserUseCase;

public interface CommandUserUseCase extends RegistUserUseCase, UpdateUserUseCase, DeleteUserUseCase {
}
