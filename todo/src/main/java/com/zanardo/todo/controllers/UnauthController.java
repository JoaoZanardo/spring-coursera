package com.zanardo.todo.controllers;

import com.zanardo.todo.models.User.AuthDTO;
import com.zanardo.todo.models.User.UserDTO;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.services.AuthService;
import com.zanardo.todo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unauth")
public class UnauthController {
    @Autowired
    UsersService usersService;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserDTO userBody) {
        UserModel user = new UserModel(userBody);
        UserModel createdUser = this.usersService.create(user);

        return createdUser.toString();
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO data) {
        return this.authService.login(data);
    }
}
