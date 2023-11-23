package com.ssafy.realty.user.application.service;

import com.ssafy.realty.user.application.port.in.ReIssuePasswordUseCase;
import com.ssafy.realty.user.application.port.in.dto.ReIssuePasswordDto;
import com.ssafy.realty.user.application.port.out.CommandUserPort;
import com.ssafy.realty.user.application.service.UserMailGenerator.TemporaryPasswordGenerator;
import com.ssafy.realty.user.application.service.UserMailGenerator.UserMailSubjectContentGenerator;
import com.ssafy.realty.user.domain.ReIssuePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
@RequiredArgsConstructor
public class ReIssuePasswordService implements ReIssuePasswordUseCase {

    private final JavaMailSender sender;
    private final UserMailSubjectContentGenerator userMailSubjectContentGenerator;
    private final TemporaryPasswordGenerator temporaryPasswordGenerator;
    private final CommandUserPort commandUserPort;
    private final BCryptPasswordEncoder encoder;

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Override
    public void findPassword(ReIssuePasswordDto reIssuePasswordDto) throws MessagingException {
        String to = reIssuePasswordDto.getUsername();
        String rawTmpPassword = temporaryPasswordGenerator.generatePassword();

        sendTemporaryPassword(to, rawTmpPassword);

        commandUserPort.updateForReIssuePassword(ReIssuePassword.init(to, encoder.encode(rawTmpPassword)));
    }

    private void sendTemporaryPassword(String to, String rawTmpPassword) throws javax.mail.MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
        helper.setFrom(fromAddress);
        helper.setSubject(userMailSubjectContentGenerator.generateSubject());
        helper.setTo(to);
        helper.setText(userMailSubjectContentGenerator.generateContent(rawTmpPassword), true);

        sender.send(message);
    }
}
