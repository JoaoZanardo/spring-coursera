package com.zanardo.todo.adapters.Encrypter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptAdapter implements Encrypter {
    private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPassword) {
        return this.bCrypt.encode(rawPassword);
    }

    @Override
    public boolean compare(String rawPassword, String encodedPassword) {
        return this.bCrypt.matches(rawPassword, encodedPassword);
    }
}