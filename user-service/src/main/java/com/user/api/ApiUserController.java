package com.user.api;

import com.user.dto.UserDto;
import com.user.entity.UserBo;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class ApiUserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserBo userBo = userService.registerNewUser(userDto);
        return new ResponseEntity(userBo, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/fetch", method = RequestMethod.GET)
    public ResponseEntity<Optional<UserBo>> getUserById(@RequestParam Long id) {
        Optional<UserBo> user = userService.getUserById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/fetch-all", method = RequestMethod.GET)
    public ResponseEntity<Iterable<UserDto>> getAllusers() {
        Iterable<UserDto> user = userService.getAllUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
