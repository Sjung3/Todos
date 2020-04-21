package com.todos;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

//The MVC controller class of the App
//Handles all DB requests

//Maps the Controller to handle requests that match specific URI-pattern "/todos", "/api/todos/delete" etc
@Controller
public class TodoController {
    //Instance of TodoService is injected
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //Renders the UI
    @GetMapping("/todos")
    public String todosHTML() {
        return "todosHTML";
    }


    //TodoService.getTodos is invoked to retrieve all the todos
    //objects are returned to the JS create method
    @GetMapping("/api/todos")
    @ResponseBody
    public ResponseEntity<List<Todo>> getTodos() {
            List<Todo> todos = todoService.getTodos();
            return ResponseEntity.ok(todos);
    }

    //The parameter is passed from the js saveTodo method using @RequestBody
    //@RequestBody converts the JSON formated String passed in the HTTP request body to an instance of todoData
    //Input field is passed to TodoService
    //The saved object is returned to the JS addTodo method
    @PostMapping("/api/todos")
    public ResponseEntity<Todo> saveTodo(@RequestBody TodoData data) {
        Todo saved = todoService.save(data.getInput());
        return ResponseEntity.ok(saved);
    }

    //deleteTodo method is called and the object from JS is passed in to the method
    @PostMapping("/api/todos/delete")
    @ResponseBody
    public void deleteTodo(@RequestBody TodoData data) {
        todoService.deleteTodo(data.getInput());
    }
}

