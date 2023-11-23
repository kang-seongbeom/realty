package com.ssafy.realty.user.application.port.in;

import com.ssafy.realty.user.application.port.in.dto.ReIssuePasswordDto;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public interface ReIssuePasswordUseCase {
    void findPassword(ReIssuePasswordDto reIssuePasswordDto) throws MessagingException, javax.mail.MessagingException;
}
