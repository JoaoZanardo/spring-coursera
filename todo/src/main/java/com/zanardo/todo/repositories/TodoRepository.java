package com.zanardo.todo.repositories;


import com.zanardo.todo.models.Todo.TodoModel;

import java.util.Optional;

public interface TodoRepository extends CoreRepository<TodoModel> {
    Optional<TodoModel> findByTitle (String title);
}
