package com.ssafy.realty.security.error;


import com.auth0.jwt.exceptions.TokenExpiredException;

public class AccessTokenExpiredException extends TokenExpiredException {
    public AccessTokenExpiredException(String message) {
        super(message, null);
    }
}
