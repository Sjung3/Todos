package com.todos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TodoRepository {

    //Instance of SessionFactory is injected
    private SessionFactory sessionFactory;

    public TodoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //Saves the todos in the DB
    //Obtaining an instance of Session by invoking the getSCurrentSession method of the sessionFactory
    //Object is stored(saved)
    public Todo saveTodo(Todo todo) {
        Session session = sessionFactory.getCurrentSession();
        session.save(todo);
        return todo;
    }

    //Called from TodoService that has a method creating a transaction
    //Retrieves all items in the list_todos database (todos table)
    //Hibernate query language that retrieves all todos
    //Query is being created and is called from TodoServices
    //Hibernate executes the query and returns all of the records in the todos table.
    public List<Todo> getTodo() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Todo";
        Query<Todo> query = session.createQuery(hql, Todo.class);
        return query.list();
    }

    //Deletes a specified row in the database
    //:id_todo is passed from the deleteTodo method
    public void deleteTodo(String id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE Todo WHERE id = :id_todo";
        Query query = session.createQuery(hql);
        query.setParameter("id_todo", Integer.parseInt(id));
        query.executeUpdate();
    }
}
