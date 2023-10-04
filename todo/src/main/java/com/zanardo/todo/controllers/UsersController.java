package com.zanardo.todo.controllers;

import com.zanardo.todo.models.Errors.CustomError;
import com.zanardo.todo.models.User.AuthDTO;
import com.zanardo.todo.models.User.UserDTO;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.services.AuthService;
import com.zanardo.todo.services.UsersService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/{userId}")
    public UserModel findById(@PathVariable("userId") String usersId) {
        return this.usersService.findById(usersId);
    }

    @GetMapping
    public List<UserModel> list() {
        return this.usersService.list();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserDTO userBody) {
        UserModel user = new UserModel(userBody);

        UserModel createdUser = this.usersService.create(user);

        return createdUser.toString();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody AuthDTO data) {
        UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(data.accountName(), data.password());
        System.out.println("user password: " + userPassword);
        this.authenticationManager.authenticate(userPassword);

        System.out.println(userPassword.getCredentials());
        System.out.println(userPassword.getPrincipal());
        System.out.println(userPassword.getName());
        System.out.println(userPassword.getDetails());
        System.out.println(userPassword.getAuthorities());

        return "";
    }
}
