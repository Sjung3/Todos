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

    //Creates a transaction for getting all the records that is retrieved in the TodoRepository getTodos method
    //Transaction = all or nothing
    //Retrieves all todos and returns response from the TodoRepository method
    @Transactional(readOnly = true)
    public List<Todo> getTodos() {
        return repository.getTodo();
    }

    //Parameter originates from method in TodoController.
    @Transactional
    public Todo save(String input) {
        return repository.saveTodo(new Todo(input));
    }

    //deleteTodo method (delete in database) is called and the id object to be deleted is passed
    @Transactional
    public void deleteTodo(String id) {
        repository.deleteTodo(id);
    }
}