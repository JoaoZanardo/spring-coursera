package com.zanardo.todo.services;

import com.zanardo.todo.adapters.Encrypter.Encrypter;
import com.zanardo.todo.adapters.Token.Token;
import com.zanardo.todo.customExceptions.UnprocessableEntity;
import com.zanardo.todo.models.User.AuthDTO;
import com.zanardo.todo.models.User.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UsersService usersService;

    @Autowired
    Token token;

    @Autowired
    Encrypter encrypter;

    public AuthService(
            UsersService usersService,
            Token token,
            Encrypter encrypter

    ) {
        this.usersService = usersService;
        this.token = token;
        this.encrypter = encrypter;
    }

    public String login(AuthDTO data) {
        UserModel user = this.usersService.findByAccount(data.account());

        boolean isValid = this.encrypter.compare(data.password(), user.getPassword());
        if (!isValid) throw new UnprocessableEntity("Invalid password!");

        return this.token.generate(user);
    }
}
