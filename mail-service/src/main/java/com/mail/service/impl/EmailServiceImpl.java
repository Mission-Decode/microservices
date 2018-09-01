package com.mail.service.impl;

import com.mail.dto.UserDto;
import com.mail.entity.MailBo;
import com.mail.repository.MailRepository;
import com.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailRepository mailRepository;

    @Override
    public void send(UserDto userDto) {
        if (userDto != null) {

            MailBo mailBo = new MailBo();
            mailBo.setTo(userDto.getEmail());
            mailBo.setSubject("Test");
            mailBo.setText("Test");
            mailBo.setSentDate(new Date());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailBo.getTo());
            message.setSubject(mailBo.getSubject());
            message.setText(mailBo.getText());

            mailRepository.save(mailBo);

            mailSender.send(message);
        }
    }
}
