package com.zanardo.todo.models.Todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="todo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String body;

    private Date createAt;

    public TodoModel (
            TodoDTO todo
    ) {
         this.title = todo.title();
         this.body = todo.body();
         this.createAt = new Date();
    }
}
