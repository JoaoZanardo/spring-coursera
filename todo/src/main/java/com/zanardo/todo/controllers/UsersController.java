package com.zanardo.todo.controllers;

import com.zanardo.todo.models.User.AuthDTO;
import com.zanardo.todo.models.User.UserDTO;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/{userId}")
    public UserModel findById(@PathVariable("userId") String usersId) {
        return this.usersService.findById(usersId);
    }

    @GetMapping
    public List<UserModel> list() {
        return this.usersService.list();
    }
}
