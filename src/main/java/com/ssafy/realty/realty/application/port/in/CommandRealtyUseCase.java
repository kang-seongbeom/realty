package com.ssafy.realty.realty.application.port.in;

import com.ssafy.realty.realty.application.port.in.command.DeleteRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.command.SaveRealtyUseCase;
import com.ssafy.realty.realty.application.port.in.command.UpdateRealtyUseCase;

public interface CommandRealtyUseCase extends SaveRealtyUseCase, UpdateRealtyUseCase, DeleteRealtyUseCase {

}
