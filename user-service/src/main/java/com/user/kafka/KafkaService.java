package com.user.kafka;

import com.user.utilities.CustomMapper;
import com.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void pushToKafka(String topic, UserDto userDto) {
        logger.info("sending payload='{}' to topic='{}'", CustomMapper.objectToJson(userDto), topic);
        kafkaTemplate.send(topic, CustomMapper.objectToJson(userDto));
    }
}
