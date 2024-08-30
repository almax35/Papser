package com.example.parser.utils;

import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User convertToEntity (UserRegistrationDto userRegistrationDto){
        return new User(userRegistrationDto.getLogin(), userRegistrationDto.getPassword());
    }
}
