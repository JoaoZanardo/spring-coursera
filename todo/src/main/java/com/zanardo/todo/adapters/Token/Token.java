package com.zanardo.todo.adapters.Token;

import com.zanardo.todo.models.User.UserModel;

public interface Token {
    String generate(UserModel user);
    String validate(String token);
}
