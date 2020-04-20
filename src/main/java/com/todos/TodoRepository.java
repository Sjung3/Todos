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

    //Saves the todos in the database
    public Todo saveTodo(Todo todo) {
        //Obtaining an instance of Session by invoking the getSCurrentSession method of the sessionFactory
        Session session = sessionFactory.getCurrentSession();
        //Todo object is stored(saved)
        session.save(todo);
        return todo;
    }

    //Called from TodoService that has a method creating a transaction
    //Retrieves all items in the list_todos database (todos table)
    public List<Todo> getTodo() {
        Session session = sessionFactory.getCurrentSession();
        //Hibernate query language that retrieves all todos
        String hql = "from Todo";
        //Query is being created and is called from TodoServices
        Query<Todo> query = session.createQuery(hql, Todo.class);
        //Hibernate executes the query and returns all of the records in the todos table.
        return query.list();
    }

    //Deletes a specified todo in the database
    public void deleteTodo(String id) {
        Session session = sessionFactory.getCurrentSession();
        //:id_todo is passed from the deleteTodo method
        String hql = "DELETE Todo WHERE id = :id_todo";
        Query query = session.createQuery(hql);
        query.setParameter("id_todo", Integer.parseInt(id));
        query.executeUpdate();
    }
}
