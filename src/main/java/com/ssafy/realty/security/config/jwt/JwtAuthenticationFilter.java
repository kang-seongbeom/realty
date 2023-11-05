package com.ssafy.realty.security.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import com.ssafy.realty.security.dto.RequestLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtManger jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestLoginDto loginDto = null;

        try {
            loginDto = objectMapper.readValue(request.getInputStream(), RequestLoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return this.authenticationProvider.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)   authResult.getPrincipal();
        String jwtAccessToken = jwtTokenProvider.createAccessToken(principalDetails);

        response.addHeader(jwtProperties.getAccessTokenHeader(), jwtProperties.getTokenPrefix() + jwtAccessToken);
    }
}
