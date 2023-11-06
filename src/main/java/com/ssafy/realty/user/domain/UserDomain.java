package com.ssafy.realty.user.domain;

import com.ssafy.realty.common.Role;
import lombok.Getter;
import lombok.Value;


public class UserDomain {
    @Getter private UserDomainId userDomainId;
    @Getter private final UserDomainData userDomainData;

    private UserDomain(UserDomainData userDomainData) {
        this.userDomainData = userDomainData;
    }

    private UserDomain(UserDomainId userDomainId, UserDomainData userDomainData) {
        this.userDomainId = userDomainId;
        this.userDomainData = userDomainData;
    }

    public static UserDomain init(String username, String password, String nickname){
        return new UserDomain(new UserDomainData(username, password, nickname, Role.USER));
    }

    public static UserDomain init(Long id, String username, String password, String nickname){
        return new UserDomain(new UserDomainId(id), new UserDomainData(username, password, nickname, Role.USER));
    }

    @Value
    public static class UserDomainId{
        Long value;
    }

    @Value
    public static class UserDomainData {
        String username;
        String password;
        String nickname;
        Role role;
    }
}
