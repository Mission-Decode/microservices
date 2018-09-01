package com.mail.service;

import com.mail.dto.UserDto;

public interface EmailService {

    void send(UserDto userDto);
}
