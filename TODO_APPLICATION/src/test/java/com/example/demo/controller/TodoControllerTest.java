package com.example.demo.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

import com.example.demo.bean.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.security.Security;

@WebMvcTest(TodoRepository.class)
@Import(Security.class)
public class TodoControllerTest {

	@Autowired
	private TodoRepository todoRepository;

	@Test

	public void a() {
		Todo todo = new Todo();
		todo.setId(1);
		todo.setName("JUNIT");

		todoRepository.save(todo);

		Assertions.assertThat(todo.getId()).isGreaterThan(0);

	}

	@Test

	public void b() {
		Todo todo = todoRepository.findById(1);

		Assertions.assertThat(todo.getId()).isEqualTo(1);

	}

	@Test

	public void c() {

		List<Todo> todos = todoRepository.getAllTodos();

		Assertions.assertThat(todos.size()).isGreaterThan(0);

	}

	@Test

	public void d() {

		Todo todoById = todoRepository.findById(1);
		todoById.setName("JUNIT");

		Todo updateTodo = todoRepository.update(todoById);

		Assertions.assertThat(updateTodo.getName()).isEqualTo("JUNIT");

	}

	@Test

	public void e() {

		todoRepository.delete(1);

		Todo todoById = todoRepository.findById(1);

		Assertions.assertThat(todoById).isNull();

	}

}
