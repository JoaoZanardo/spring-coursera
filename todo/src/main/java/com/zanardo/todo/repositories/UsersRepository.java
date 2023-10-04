package com.zanardo.todo.repositories;

import com.zanardo.todo.models.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserModel, String> {
    UserDetails findByAccountName (String accountName);
}
