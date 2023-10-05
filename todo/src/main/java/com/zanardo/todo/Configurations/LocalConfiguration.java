package com.zanardo.todo.Configurations;

import com.zanardo.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfiguration {

    @Autowired
    TodoRepository todoRepository;

    @Bean
    public CommandLineRunner execute () {
        return args -> {
            System.out.println("Running in Local");
        };
    }
}
