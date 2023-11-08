package com.ssafy.realty.user.application.port.out.command;

import com.ssafy.realty.user.domain.UserDomain;

public interface UpdateUserPort {
    void update(UserDomain userDomain);
}
