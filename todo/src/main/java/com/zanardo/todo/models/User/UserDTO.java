package com.zanardo.todo.models.User;

public record UserDTO(
        String accountName,
        String password,
        UserRole role
) {}
