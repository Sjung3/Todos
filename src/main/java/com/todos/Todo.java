package com.todos;

import javax.persistence.*;

//The class represents a table in the list_todos database
@Entity
//Specifies the todos table that the Todo class is mapped to
@Table(name = "todos")
public class Todo {
    @Id
//Primary key value is generated automatically
    //IDENTITY = auto-incremented
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "input", nullable = false, length = 300)
    private String input;

    //This constructor creates the objects after Hibernate has retrieved the todo records from the database
    public Todo() {
    }

    public Todo(String input) {
        this.input = input;
    }

    public Integer getId() {
        return id;
    }

    public String getInput() {
        return input;
    }
}