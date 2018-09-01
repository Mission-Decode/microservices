package com.user.service;

import com.user.entity.UserBo;
import com.user.dto.UserDto;

import java.util.Optional;

public interface UserService {

    UserBo registerNewUser(UserDto userDto);

    Optional<UserBo> getUserById(Long id);

    Iterable<UserDto> getAllUser();

}
