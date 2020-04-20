package com.todos;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

//The MVC controller class of the App
//Handles all todo requests

@Controller
//Maps the Controller to handle requests that match specific URI-pattern "/todos", "/api/todos/delete" etc
public class TodoController {
    //Instance of TodoService is injected
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //Handles the todosHTML file so the user interface is rendered
    @GetMapping("/todos")
    public String todosHTML() {
        return "todosHTML";
    }


    //TodoService.getTodos is invoked to retrieve all the todos
    @GetMapping("/api/todos")
    @ResponseBody
    public ResponseEntity<List<Todo>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        //todos are returned to the js create method
        return ResponseEntity.ok(todos);
    }


    @PostMapping("/api/todos")
    //The parameter is passed from the js saveTodo method using @RequestBody
    //@RequestBody converts the JSON formated String passed in the HTTP request body to an instance of todoData
    public ResponseEntity<Todo> saveTodo(@RequestBody TodoData data) {
        //Input field is passed to TodoService
        Todo saved = todoService.save(data.getInput());
        //The saved todo is returned to the js addTodo method
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/api/todos/delete")
    @ResponseBody
    public void deleteTodo(@RequestBody TodoData data) {
        //deleteTodo method is called and the object from js is passed in to the method
        todoService.deleteTodo(data.getInput());
    }
}

