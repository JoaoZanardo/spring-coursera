package com.zanardo.todo.services;

import com.zanardo.todo.adapters.Encrypter.Encrypter;
import com.zanardo.todo.customExceptions.Conflict;
import com.zanardo.todo.customExceptions.NotFound;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.models.User.UserModel;
import com.zanardo.todo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    Encrypter encrypter;

    public UsersService (
            UsersRepository usersRepository
    ) {
        this.usersRepository = usersRepository;
    }

    public UserModel findById(String todoId) {
        Optional<UserModel> optionalUser = this.usersRepository.findById(todoId);

        if (optionalUser.isEmpty()) throw new NotFound("User not found!");

        return optionalUser.get();
    }

    public UserModel findByAccount(String account) {
        Optional<UserModel> optionalUser = this.usersRepository.findByAccount(account);
        if (optionalUser.isEmpty()) throw new NotFound("User not found!");

        return optionalUser.get();
    }

    public UserModel create (UserModel user) {
        boolean existsAccount = this.existsAccount(user.getAccount());
        if (existsAccount) throw new Conflict("Account name already registered!");

        user.setPassword(this.encrypter.encode(user.getPassword()));

        return this.usersRepository.save(user);
    }

    public List<UserModel> list () {
        return this.usersRepository.findAll();
    }

    public boolean existsAccount(String account) {
        Optional<UserModel> optionalUser = this.usersRepository.findByAccount(account);

        return optionalUser.isPresent();
    }
}
