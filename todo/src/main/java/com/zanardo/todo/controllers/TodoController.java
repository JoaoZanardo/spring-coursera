package com.zanardo.todo.controllers;

import com.zanardo.todo.customExceptions.BadRequest;
import com.zanardo.todo.models.Todo.TodoDTO;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.services.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name="Todo", description ="This Controller Is top")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todo/{todoId}")
    public TodoModel findById(@PathVariable("todoId") String todoId) {
        return this.todoService.findById(todoId);
    }

    @Operation(
            responses = @ApiResponse(responseCode = "400", content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = TodoModel.class)))
    )
    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody TodoDTO todoBody) {
        TodoModel todo = new TodoModel(todoBody);

        this.todoService.create(todo);

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
        if (todoBody.title() == null) throw new BadRequest("Title is required!");
        if (todoBody.body() == null) throw new BadRequest("Body is required!");

        this.todoService.update(todoId, todoBody);

        return "Updated successfully!";
    }

    @GetMapping("/todo")
    public List<TodoModel> list () {
        return this.todoService.list();
    }
}
