package com.zanardo.todo.controllers;

import com.zanardo.todo.models.User.AuthDTO;
import com.zanardo.todo.models.User.UserDTO;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.services.JwtService;
import com.zanardo.todo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unauth")
public class UnauthController {
    @Autowired
    UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserDTO userBody) {
        UserModel user = new UserModel(userBody);
        UserModel createdUser = this.usersService.create(user);

        return createdUser.toString();
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO data) {
        UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(data.account(), data.password());
        Authentication auth = this.authenticationManager.authenticate(userPassword);

        return this.jwtService.generate((UserModel) auth.getPrincipal());
    }
}
