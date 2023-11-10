package com.ssafy.realty.user.application.port.in.dto;

import com.ssafy.realty.common.Role;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateDto {

    Long id;
    String password;
    String nickname;
    Role role;
}
