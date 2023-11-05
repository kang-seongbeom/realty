package com.ssafy.realty.security.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.realty.security.config.auth.PrincipalDetails;
import com.ssafy.realty.security.entity.User;
import com.ssafy.realty.security.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtManger {

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    public String createAccessToken(PrincipalDetails principalDetails) {
        return JWT.create()
                .withSubject("token") //아무거나 써도 됨.
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenExpirationTime()))
                .withClaim("id", principalDetails.getUser().getId()) // 발행 유저정보 저장
                .withClaim("username", principalDetails.getUser().getUsername())
                .withClaim("role", principalDetails.getUser().getRole().toString())
                .sign(Algorithm.HMAC512(jwtProperties.getAccessTokenSecret())); //고윳값
    }

    public Optional<String> resolveAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(jwtProperties.getAccessTokenHeader()));
    }

    public String getUsername(String token) {
        token = token.replace(jwtProperties.getTokenPrefix(), "");

        return JWT.require(Algorithm.HMAC512(jwtProperties.getAccessTokenSecret())).build().verify(token).
                getClaim("username").asString();

    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);

        if (username != null) {
            User user = userRepository.findByUsername(username);
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            return new UsernamePasswordAuthenticationToken(principalDetails,
                    null,
                    principalDetails.getAuthorities());
        }
        return null;
    }

    public PrincipalDetails getPrincipalDetails(String token) {
        String username = getUsername(token);
        if (username != null) {
            User user = userRepository.findByUsername(username);
            return new PrincipalDetails(user);
        }
        return null;
    }

    public Claims getClaimsFromJwt(String jwt) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getAccessTokenSecret())
                .build();

        return parser.parseClaimsJwt(jwt).getBody();
    }

    public boolean isTokenValid(String token){
        token = token.replace(jwtProperties.getTokenPrefix(), "");
        return getUsername(token) != null;
    }
}
