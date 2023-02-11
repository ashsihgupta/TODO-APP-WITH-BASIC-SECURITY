package com.example.demo.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.Todo;

@Repository
public class TodoRepository {
	
	private List<Todo> list = new LinkedList<Todo>();

    public List<Todo> getAllTodos() {
        return list;
    }

    public Todo findById(int id){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public List<Todo> search(String name) {
        return list.stream().filter(x -> x.getName().startsWith(name)).collect(Collectors.toList());
    }

    public Todo save(Todo t) {
        Todo todo = new Todo();
        todo.setId(t.getId());
        todo.setName(t.getName());
        
        list.add(todo);
        return todo;
    }

    public String delete(Integer id) {
        list.removeIf(x -> x.getId() == (id));
        return null;
    }

    public Todo update(Todo todo) {
        int idx = 0;
        int id = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (todo.getId())) {
                id = todo.getId();
                idx = i;
                break;
            }
        }

        Todo todo1 = new Todo();
        todo1.setId(id);
        todo1.setName(todo.getName());
        
        list.set(idx, todo);
        return todo1;
    }

}
