package com.claude.lesson_6_config.repositories;

import com.claude.lesson_6_config.models.Book;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    Book findById(Long id);
    List<Book> findAll();
    List<Book> findAvailable();
    void delete(Long id);
}
