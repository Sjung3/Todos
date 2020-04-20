package com.todos;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class TodoService {
    //Instance of TodoRepository is injected to the class
    private TodoRepository repository;

    //The constructor
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    //Creates a transaction for getting all todo records that is retrieved in the TodoRepository getTodos method
    //Transaction = all or nothing
    @Transactional(readOnly = true)
    public List<Todo> getTodos() {
        //Retrieves all todos
        return repository.getTodo();
    }

    @Transactional
    //Parameter originates from method in TodoController.
    public Todo save(String input) {
        //saveTodo method in TodoRepository is called and a new Todo object is passed
        return repository.saveTodo(new Todo(input));
    }

    @Transactional
    public void deleteTodo(String id) {
        //deleteTodo method (delete in database) is called and the id object to be deleted is passed
        repository.deleteTodo(id);
    }
}