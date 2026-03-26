package com.example.hibernate.controller;

import com.example.hibernate.dto.PaddedTitleResponse;
import com.example.hibernate.entity.Book;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

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
    public List<PaddedTitleResponse> getPaddedTitles() {
        logger.info("Fetching padded titles using custom LPAD function");
        String jpql = """
            select new com.example.hibernate.dto.PaddedTitleResponse(
                b.id,
                function('lpad', b.title, 30, '*')
            )
            from Book b
            """;
        return entityManager.createQuery(jpql, PaddedTitleResponse.class).getResultList();
    }
}
