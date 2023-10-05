package com.zanardo.todo.models.User;

public record UserDTO(
        String account,
        String password,
        UserRole role
) {}
