package com.ssafy.realty.user.adapter.in.web.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePayload {

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Za-z])[A-Za-z0-9]{8,}$", message = "비밀번호는 최소 8자 이상, 영문과 숫자를 모두 포함해야 합니다.")
    @NotNull
    String password;

    @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
    @NotNull
    String nickname;
}
