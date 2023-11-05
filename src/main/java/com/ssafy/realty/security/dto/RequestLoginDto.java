package com.ssafy.realty.security.dto;

import lombok.Data;

@Data
public class RequestLoginDto {
    private String username;
    private String password;
}
