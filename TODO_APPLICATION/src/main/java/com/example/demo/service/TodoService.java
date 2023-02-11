package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
    private TodoRepository repository;

    public Todo saveTodo(Todo Todo) {
        return repository.save(Todo);
    }

    public List<Todo> getTodos() {
        return repository.getAllTodos();
    }

    public Todo getTodoById(int id) {
        return repository.findById(id);
    }

    public String deleteTodo(int id) {
        repository.delete(id);
        return "Todo Deleted !! " + id;
    }

    public Todo updateTodo(Todo Todo) {
       return repository.update(Todo);
    }

}
