package com.ssafy.realty.user.application.port.out;

import com.ssafy.realty.user.application.port.out.command.DeleteUserPort;
import com.ssafy.realty.user.application.port.out.command.ReIssuePasswordPort;
import com.ssafy.realty.user.application.port.out.command.RegistUserPort;
import com.ssafy.realty.user.application.port.out.command.UpdateUserPort;

public interface CommandUserPort extends RegistUserPort, UpdateUserPort, DeleteUserPort, ReIssuePasswordPort {
}
