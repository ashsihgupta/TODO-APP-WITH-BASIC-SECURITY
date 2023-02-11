package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Todo;
import com.example.demo.service.TodoService;


@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService service;

	@PostMapping
	public Todo addTodo(@RequestBody Todo Todo) {
		return service.saveTodo(Todo);
	}

	@GetMapping
	public Object findAllTodos() {
		if(service.getTodos().size()<1)
			return "NO TODO TO DISPLAY PLEASE ADD TODO";
		return service.getTodos();
	}

	@GetMapping("{id}")
	public Object findTodoById(@PathVariable int id) throws Exception {
		if(service.getTodoById(id)==null)
			return "TODO NOT FOUND WITH ID "+id;
			
		return service.getTodoById(id);
	}

	@PutMapping
	public Todo updateTodo(@RequestBody Todo Todo) {
		return service.updateTodo(Todo);
	}

	@DeleteMapping("{id}")
	public Object deleteTodo(@PathVariable int id) {
		if(service.getTodoById(id)==null)
			return "TODO NOT FOUND WITH ID "+id;
		return service.deleteTodo(id);
	}

}
