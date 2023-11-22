package com.ssafy.realty.realty.application.port.out;

import com.ssafy.realty.realty.application.port.out.command.DeleteRealtyPort;
import com.ssafy.realty.realty.application.port.out.command.SaveRealtyPort;
import com.ssafy.realty.realty.application.port.out.command.UpdateRealtyPort;
import com.ssafy.realty.realty.application.port.out.command.ViewIncreasePort;

public interface CommandRealtyPort extends SaveRealtyPort, UpdateRealtyPort, DeleteRealtyPort, ViewIncreasePort {
}
