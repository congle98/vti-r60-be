package com.example.demo_r60_be.controller;

import com.example.demo_r60_be.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/todo")
public class TodoController {
    static List<Todo> todoList = new ArrayList<>();

    static {
        todoList.add(new Todo(1,"Sáng dậy sớm đánh răng"));
        todoList.add(new Todo(2,"Trưa đi uống cà phê"));
        todoList.add(new Todo(3, "Chiều đi đá bóng"));
        todoList.add(new Todo(4,"Tối đi tán gái"));
    }


    @GetMapping
    public ResponseEntity<List<Todo>> findAll(){
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody Todo todo){
        if(todoList.isEmpty()) {
            todo.setId(1);
            todoList.add(todo);
        }
        else {
            todo.setId(todoList.get(todoList.size()-1).getId()+1);
            todoList.add(todo);
        }
        return new ResponseEntity<>("Thêm mới thành công", HttpStatus.CREATED);
    }
}
