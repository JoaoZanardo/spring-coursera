package com.zanardo.todo.services;

import com.zanardo.todo.customExceptions.Conflict;
import com.zanardo.todo.customExceptions.NotFound;
import com.zanardo.todo.models.Todo.TodoDTO;
import com.zanardo.todo.models.Todo.TodoModel;
import com.zanardo.todo.repositories.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {
    private final TodoDTO todoDTO = new TodoDTO("Title", "Body");
    private final TodoModel todo = new TodoModel(todoDTO);

    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.todoService = new TodoService(this.todoRepository);
    }

    @Test
    void shouldFindById() {
        this.mockValidFindByIdReturn();

        TodoModel actualTodo = this.todoService.findById("todoId");

        assertEquals(this.todo, actualTodo);
    }

    @Test
    void shouldThrowNotFoundWhenFindById() {
        Mockito.when(this.todoRepository.findById("todoId")).thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> this.todoService.findById("todoId"));
    }

    @Test
    void shouldCreate() {
        Mockito.when(this.todoRepository.save(this.todo)).thenReturn(this.todo);

        assertEquals(this.todoService.create(this.todo), this.todo);
    }

    @Test
    void shouldThrowConflictWhenCreate() {
        Mockito.when(this.todoRepository.findByTitle(todo.getTitle())).thenReturn(Optional.of(this.todo));

        assertThrows(Conflict.class, () -> this.todoService.create(this.todo));
    }

    @Test
    void shouldUpdate() {
        this.mockValidFindByIdReturn();

        this.todoService.update("todoId", this.todoDTO);

        Mockito.verify(this.todoRepository).save(this.todo);
    }

    @Test
    void shouldDelete() {
        this.mockValidFindByIdReturn();

        this.todoService.delete("todoId");

        Mockito.verify(this.todoRepository).delete(this.todo);
    }

    @Test
    void shouldList() {
        List<TodoModel> todoList = new ArrayList<>();
        Mockito.when(this.todoRepository.findAll()).thenReturn(todoList);

        List<TodoModel> actualTodoList = this.todoService.list();

        assertEquals(todoList, actualTodoList);
    }

    private void mockValidFindByIdReturn() {
        Mockito.when(this.todoRepository.findById("todoId")).thenReturn(Optional.of(this.todo));
    }
}