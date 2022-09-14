package com.example.buoi7_th2.Services;

import com.example.buoi7_th2.Models.Todo;

import java.util.List;

public interface ITodoService {
    List<Todo> getTodos();

    Todo getTodoById(Long id);

    Todo insert(Todo todo);

    void updateTodo(Long id, Todo todo);

    void deleteTodo(Long todoId);
}
