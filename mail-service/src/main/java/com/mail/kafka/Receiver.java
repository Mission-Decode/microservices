package com.mail.kafka;

import com.mail.dto.UserDto;
import com.mail.service.EmailService;
import com.mail.utilities.CustomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.topic.userCreated}")
    public void receive(String payload) {
        UserDto userDto = CustomMapper.jsonToObject(payload, UserDto.class);
        logger.info("[mail-service] Sending mail to: "+ userDto.getEmail());
        emailService.send(userDto);
        latch.countDown();
    }

}
