package com.todos;

import javax.persistence.*;

//The class represents a table in the DB
@Entity
//Specifies the table that the class is mapped to
@Table(name = "todos")
public class Todo {
    //Primary key value is generated automatically
    //IDENTITY = auto-incremented
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "input", nullable = false, length = 300)
    private String input;

    //Creates the objects after Hibernate has retrieved the records from the database
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