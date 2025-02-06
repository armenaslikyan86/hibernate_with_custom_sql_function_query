package com.example.hibernate.controller;

import com.example.hibernate.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<Book> getAllBooks() {
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }

    @GetMapping("/padded-titles")
    public List<Map<String, Object>> getPaddedTitles() {
        String jpql = "SELECT b.id, FUNCTION('lpad', b.title, 30, '*') FROM Book b";
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