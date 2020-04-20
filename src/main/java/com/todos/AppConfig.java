package com.todos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

//Tells Spring that beans are defined in this class
@Configuration
//Scans the specified package for annotated methods
@ComponentScan("com.todos")
@EnableTransactionManagement
public class AppConfig {
    //Instance of DataSource is injected to the class
    private DataSource dataSource;

    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //Session = main interface for storing/retrieving entities
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        //Instance of SessionFactory is created
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        //Injection of datasource
        sessionFactoryBean.setDataSource(dataSource);
        //Specifies which package Hibernate should scan in order to find entity classes
        sessionFactoryBean.setPackagesToScan("com.todos");
        return sessionFactoryBean;
    }

    //Creates bean to ensure @Transactional annotation can be used
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
