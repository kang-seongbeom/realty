package com.ssafy.realty.user.application.port.in;

import com.ssafy.realty.user.application.port.in.dto.ReIssuePasswordDto;

public interface ReIssuePasswordUseCase {
    void findPassword(ReIssuePasswordDto reIssuePasswordDto) throws javax.mail.MessagingException;
}
