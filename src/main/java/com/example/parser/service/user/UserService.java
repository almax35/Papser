package com.example.parser.service.user;

import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.entity.User;

public interface UserService {
    boolean isUserExist(String username);
    User getUserByLogin(String username);

    void save(UserRegistrationDto userRegistrationDto);
}
