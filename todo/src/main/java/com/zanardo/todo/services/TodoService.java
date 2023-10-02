package com.zanardo.todo.services;

import com.zanardo.todo.customExceptions.BadRequest;
import com.zanardo.todo.customExceptions.Conflict;
import com.zanardo.todo.customExceptions.NotFound;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.repositories.TodoRepository;
import com.zanardo.todo.models.Todo.UpdateTodoModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public TodoModel findById(String todoId) {
        Optional<TodoModel> optionalTodo = this.todoRepository.findById(todoId);

        if (optionalTodo.isEmpty()) throw new NotFound("Todo not found!");

        return optionalTodo.get();
    }

    public TodoModel create(TodoModel todo) {
        if (this.existsTitle(todo)) throw new Conflict("Title already registered!");

        return this.todoRepository.save(todo);
    }

    public void update(String todoId, UpdateTodoModelDto data) {
        TodoModel todo = this.findById(todoId);

        String title = data.title();
        String body = data.body();

        if (title == null) throw new BadRequest("Title is required!");
        if (body == null) throw new BadRequest("Body is required!");

        todo.setTitle(title);
        todo.setBody(body);

        this.todoRepository.save(todo);
    }

    public void delete(String todoId) {
        TodoModel todo = this.findById(todoId);

        this.todoRepository.delete(todo);
    }

    public List<TodoModel> list () {
        return this.todoRepository.findAll();
    }

    private boolean existsTitle (TodoModel todo) {
        Optional<TodoModel> optionalTodo = this.todoRepository.findByTitle(todo.getTitle());

        return optionalTodo.isPresent();
    }
}