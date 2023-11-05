package com.ssafy.realty.security.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final HttpStatus UNAUTHORIZED=HttpStatus.UNAUTHORIZED;
    private static final ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof BadCredentialsException) {
            response.setCharacterEncoding("utf-8");
            ErrorResponse build =new ErrorResponse(ErrorStatues.findByErrorCode(ErrorCode.E4011),ErrorCode.E4011);
            String s = objectMapper.writeValueAsString(build);
            response.setContentType("application/json");
            response.setStatus(UNAUTHORIZED.value());
            response.getWriter().write(s);
            response.getWriter().flush();
        }
    }

}
