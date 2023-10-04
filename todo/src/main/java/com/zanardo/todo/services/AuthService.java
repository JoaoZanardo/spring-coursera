package com.zanardo.todo.services;

import com.zanardo.todo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        System.out.println("UIHDUHAS UDHAS UIDHASUI DHASID HASI HDAISDHUI");
        return this.usersRepository.findByAccountName(accountName);
    }
}
