package com.zanardo.todo.controllers;

import com.zanardo.todo.models.Todo.CreateTodoModelDTO;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.services.TodoService;
import com.zanardo.todo.models.Todo.UpdateTodoModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
public class TodoController {
    @Value("${application.name}")
    private String appName;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<TodoModel> findById(@PathVariable("todoId") String todoId) {
        TodoModel todo = this.todoService.findById(todoId);

        return ResponseEntity.ok(todo);
    }

    @PostMapping("/todo")
    public ResponseEntity create(@RequestBody CreateTodoModelDTO todoBody) {
        TodoModel todo = new TodoModel(todoBody);

        TodoModel createdTodo = this.todoService.create(todo);

        return ResponseEntity.ok("Created successfully!");
    }

    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity delete(@PathVariable("todoId") String todoId) {
        this.todoService.delete(todoId);

        return ResponseEntity.ok("Deleted successfully!");
    }

    @PutMapping("/todo/{todoId}")
    public ResponseEntity update(
            @PathVariable("todoId") String todoId,
            @RequestBody UpdateTodoModelDto todoBody
    ) {
        this.todoService.update(todoId, todoBody);

        return ResponseEntity.ok("Updated successfully!");
    }

    @GetMapping("/todo")
    public ResponseEntity<List<TodoModel>> list () {
        List<TodoModel> todoList = this.todoService.list();

        return ResponseEntity.ok(todoList);
    }
}
