package com.example.hibernate;

import com.example.hibernate.entity.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication(scanBasePackages = "com.example.hibernate")
@EntityScan(basePackages = "com.example.hibernate.entity")
@Component
@EnableTransactionManagement
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        try {
            SpringApplication.run(Main.class, args);
            logger.info("Application started successfully");
        } catch (Exception e) {
            logger.error("Failed to start application", e);
            throw e;
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        try {
            logger.info("Initializing sample data...");
            insertSampleData();
            logger.info("Sample data initialization completed successfully");
        } catch (Exception e) {
            logger.error("Error initializing sample data", e);
            throw e;
        }
    }

    private void insertSampleData() {
        try {
            logger.info("Creating sample books...");
            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
            Book book2 = new Book("1984", "George Orwell");
            Book book3 = new Book("To Kill a Mockingbird", "Harper Lee");

            entityManager.persist(book1);
            entityManager.persist(book2);
            entityManager.persist(book3);

            entityManager.flush();
            logger.info("Successfully created sample books");
        } catch (Exception e) {
            logger.error("Error creating sample books", e);
            throw e;
        }
    }
}
