package com.claude.lesson_6_config.repositories;

import com.claude.lesson_6_config.models.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("dev")
public class InMemoryBookRepository implements BookRepository {
    private Map<Long, Book> books = new HashMap<>();
    private Long idCounter = 1L;

    public InMemoryBookRepository() {
        System.out.println("[DEV] InMemoryBookRepository initialized");
    }

    public void save(Book book) {
        if (book == null) {
            System.out.println("[DEV] Cannot save book. Book is null");
            return;
        }
        if (book.getId() == null) {
            book = new Book(idCounter++, book.getTitle(), book.getAuthor(), book.getIsbn());
        }
        books.put(book.getId(), book);
        System.out.println("[DEV] Saved book: " + book.getTitle());
    }

    public Book findById(Long id) {
        Book book = books.get(id);
        if (book == null) {
            System.out.println("[DEV] Book not found: [id]");
            return null;
        }
        return book;
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public List<Book> findAvailable() {
        return books.values().stream().filter(Book::isAvailable).toList();
    }

    public void delete(Long id) {
        books.remove(id);
        System.out.println("[DEV] Deleted book: " + id);
    }

    @PostConstruct
    void postConstruct() {
        this.save(new Book(null, "Clean Code", "Robert Martin", "ISBN-001"));
        this.save(new Book(null, "Spring in Action", "Craig Walls", "ISBN-002"));
        this.save(new Book(null, "Effective Java", "Joshua Bloch", "ISBN-003"));

        System.out.println("[DEV] Test data loaded: 3 books");
    }
}
