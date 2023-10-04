package com.zanardo.todo.services;

import com.zanardo.todo.customExceptions.NotFound;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.repositories.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {
    @Test
    void create() {
        boolean thrown = false;

        try {
            this.todoRepository.findById("todoId");
        } catch (NotFound e) {
            thrown = true;
        }

        Assertions.assertTrue(thrown);
    }
}