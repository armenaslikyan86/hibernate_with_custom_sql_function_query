package com.example.hibernate;

import com.example.hibernate.entity.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootApplication
@EntityScan(basePackages = "com.example.hibernate.entity")
@Component
public class Main {
    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        // Insert sample data
        insertSampleData();
    }

    private void insertSampleData() {
        try {
            // Create and save sample books
            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
            Book book2 = new Book("1984", "George Orwell");
            Book book3 = new Book("To Kill a Mockingbird", "Harper Lee");

            entityManager.persist(book1);
            entityManager.persist(book2);
            entityManager.persist(book3);

            entityManager.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}