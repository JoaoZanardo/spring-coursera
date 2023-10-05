package com.zanardo.todo.controllers;

import com.zanardo.todo.customExceptions.BadRequest;
import com.zanardo.todo.models.Todo.TodoDTO;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.services.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@Tag(name="Todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/{todoId}")
    public TodoModel findById(@PathVariable("todoId") String todoId) {
        return this.todoService.findById(todoId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody TodoDTO todoBody) {
        TodoModel todo = new TodoModel(todoBody);

        this.todoService.create(todo);

        return "Created successfully!";
    }

    @DeleteMapping("/{todoId}")
    public String delete(@PathVariable("todoId") String todoId) {
        this.todoService.delete(todoId);

        return "Deleted successfully!";
    }

    @PutMapping("/{todoId}")
    public String update(
            @PathVariable("todoId") String todoId,
            @RequestBody TodoDTO todoBody
    ) {
        if (todoBody.title() == null) throw new BadRequest("Title is required!");
        if (todoBody.body() == null) throw new BadRequest("Body is required!");

        this.todoService.update(todoId, todoBody);

        return "Updated successfully!";
    }

    @GetMapping("/")
    public List<TodoModel> list () {
        return this.todoService.list();
    }
}
