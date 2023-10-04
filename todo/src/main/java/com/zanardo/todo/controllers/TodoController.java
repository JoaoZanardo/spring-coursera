package com.zanardo.todo.controllers;

import com.zanardo.todo.models.Todo.TodoDTO;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todo/{todoId}")
    public TodoModel findById(@PathVariable("todoId") String todoId) {
        return this.todoService.findById(todoId);
    }

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody TodoDTO todoBody) {
        TodoModel todo = new TodoModel(todoBody);

        TodoModel createdTodo = this.todoService.create(todo);

        return "Created successfully!";
    }

    @DeleteMapping("/todo/{todoId}")
    public String delete(@PathVariable("todoId") String todoId) {
        this.todoService.delete(todoId);

        return "Deleted successfully!";
    }

    @PutMapping("/todo/{todoId}")
    public String update(
            @PathVariable("todoId") String todoId,
            @RequestBody TodoDTO todoBody
    ) {
        this.todoService.update(todoId, todoBody);

        return "Updated successfully!";
    }

    @GetMapping("/todo")
    public List<TodoModel> list () {
        return this.todoService.list();
    }
}
