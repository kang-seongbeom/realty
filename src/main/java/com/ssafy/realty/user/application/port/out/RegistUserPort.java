package com.ssafy.realty.user.application.port.out;

import com.ssafy.realty.user.domain.UserDomain;

public interface RegistUserPort {
    void regist(UserDomain userDomain);
}
