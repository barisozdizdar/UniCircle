package com.group.hassocial.controller;
import com.group.hassocial.data.dto.UserDto;
import com.group.hassocial.exception.InvalidEmailDomainException;
import com.group.hassocial.exception.Status;
import com.group.hassocial.exception.UserAlreadyExistException;
import com.group.hassocial.service.LoginService;
import com.group.hassocial.service.RegistrationService;
import com.group.hassocial.service.SignUpService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;


@Api(value = "User Registration Rest Controller")
@RestController
@RequestMapping("/api/user")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final SignUpService signUpService;
    private final LoginService loginService;

    public RegistrationController(RegistrationService registrationService, SignUpService signUpService, LoginService loginService) {
        this.registrationService = registrationService;
        this.signUpService = signUpService;
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistException,
            InvalidEmailDomainException, ParseException {
         registrationService.register(userDto);
         return ResponseEntity.ok("User is registered half the way. Authentication email is sent!");
    }

    @GetMapping("/authenticate")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDto userDto) throws ParseException{
        return signUpService.signUp(userDto);
    }

    @PostMapping("/login")
    public Status login(@Valid @RequestBody UserDto userDto) {
        return loginService.loginUser(userDto);
    }
}