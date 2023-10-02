package com.zanardo.todo.repositories;

import com.zanardo.todo.models.Todo.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoreRepository<T> extends JpaRepository<TodoModel, String> {}
