package com.ssafy.realty.user.domain;

import lombok.Getter;
import lombok.Value;

@Value
public class ReIssuePassword {

    ReIssuePasswordUsername reissuePasswordUsername;
    ReIssuePasswordPassword reissuePasswordPassword;

    public static ReIssuePassword init(String username, String password){
       return new ReIssuePassword(
               new ReIssuePasswordUsername(username),
               new ReIssuePasswordPassword(password)
       );
    }


    @Value
    public static class ReIssuePasswordUsername {
        String username;
    }

    @Value
    public static class ReIssuePasswordPassword {
       String password;
    }
}
