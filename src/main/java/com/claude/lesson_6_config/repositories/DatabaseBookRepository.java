package com.claude.lesson_6_config.repositories;

import com.claude.lesson_6_config.models.Book;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
public class DatabaseBookRepository implements BookRepository {
    public DatabaseBookRepository() {
        System.out.println("[PROD] DatabaseBookRepository initialized (simulated)");
    }

    @Override
    public void save(Book book) {
        System.out.println("[PROD] Saving to database: " + book.getTitle());
    }

    @Override
    public Book findById(Long id) {
        System.out.println("[PROD] Querying database for book ID: " + id);
        return null;
    }

    @Override
    public List<Book> findAll() {
        System.out.println("[PROD] Fetching all books from database");
        return List.of();
    }

    @Override
    public List<Book> findAvailable() {
        System.out.println("[PROD] Querying available books from database");
        return List.of();
    }

    @Override
    public void delete(Long id) {
        System.out.println("[PROD] Deleting from database: " + id);
    }
}
