package com.example.hibernate.controller;

import com.example.hibernate.entity.Book;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }

    @GetMapping("/padded-titles")
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getPaddedTitles() {
        logger.info("Fetching padded titles using custom LPAD function");
        String jpql = "SELECT b.id, FUNCTION('lpad', b.title, 30, '*') as paddedTitle FROM Book b";
        Query query = entityManager.createQuery(jpql);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results.stream()
            .map(result -> Map.of(
                "id", result[0],
                "paddedTitle", result[1]
            ))
            .collect(Collectors.toList());
    }
}