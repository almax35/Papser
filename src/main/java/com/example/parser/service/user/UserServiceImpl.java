package com.example.parser.service.user;


import com.example.parser.dto.UserRegistrationDto;
import com.example.parser.entity.Role;
import com.example.parser.entity.User;
import com.example.parser.repository.RoleRepository;
import com.example.parser.repository.UserRepository;
import com.example.parser.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User getUserByLogin(String username) {
        return userRepository.findByLogin(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public void save(UserRegistrationDto userRegistrationDto) {
        userRegistrationDto.setPassword(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));
        User user=userMapper.convertToEntity(userRegistrationDto);
        Optional<Role> optionalRole=roleRepository.findByName("ROLE_USER");
        if (optionalRole.isPresent()){
            user.setRole(optionalRole.get());
            userRepository.save(user);
        } else {
            throw new DataSourceLookupFailureException("");
        }

    }


}