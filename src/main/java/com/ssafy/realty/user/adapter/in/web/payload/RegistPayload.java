package com.ssafy.realty.user.adapter.in.web.payload;

import lombok.Data;

@Data
public class RegistPayload {

    String username;
    String password;
    String nickname;
}
