package com.group.hassocial.service;

import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.data.model.User;
import com.group.hassocial.exception.Status;
import com.group.hassocial.repository.UserRepository;
import com.group.hassocial.security.PasswordEncoder;
import com.group.hassocial.service.interfaces.ILoginService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("loginService")
public class LoginService implements ILoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Status loginUser(UserDto userDto) {

        if (!userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return Status.FAILURE;
        }
        User user = userRepository.findByEmail(userDto.getEmail()).get();
        if (Objects.equals(user.getPasswordHash(), passwordEncoder.encodePassword(userDto))) {
            user.setLoggedIn(true);
            userRepository.save(user);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }
}
