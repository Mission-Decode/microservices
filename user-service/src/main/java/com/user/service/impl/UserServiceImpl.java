package com.user.service.impl;

import com.user.dto.UserDto;
import com.user.entity.UserBo;
import com.user.kafka.KafkaService;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.kafka.topic.userCreated}")
    private String topic;

    @Autowired
    private KafkaService kafkaService;


    @Override
    public UserBo registerNewUser(UserDto userDto) {
        UserBo userBo = new UserBo();
        userBo.setEmail(userDto.getEmail());
        userBo.setPassword(userDto.getPassword());
        userBo.setUserName(userDto.getUserName());

        UserBo user = userRepository.save(userBo);

        publishToKafka(userDto);
        return user;
    }

    private void publishToKafka(UserDto userDto) {
        kafkaService.pushToKafka(topic,userDto);
    }

    @Override
    public Optional<UserBo> getUserById(Long id) {
        Optional<UserBo> userBo = userRepository.findById(id);
        return userBo;
    }

    @Override
    public Iterable<UserDto> getAllUser() {
        Iterable<UserBo> userBos = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if (userBos != null) {
            userBos.forEach(userBo -> {
                UserDto userDto = new UserDto();
                userDto.setEmail(userBo.getEmail());
                userDto.setUserName(userBo.getUserName());
                userDto.setId(userBo.getId());
                userDtos.add(userDto);
            });
        }

        return userDtos;
    }
}
