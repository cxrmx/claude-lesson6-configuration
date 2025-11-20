package com.claude.lesson_6_config.services;

import com.claude.lesson_6_config.config.LibraryProperties;
import com.claude.lesson_6_config.models.Book;
import com.claude.lesson_6_config.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final NotificationService notificationService;
    private final LibraryProperties libraryProperties;

    public LibraryService(BookRepository bookRepository, NotificationService notificationService, LibraryProperties libraryProperties) {
        this.bookRepository = bookRepository;
        this.notificationService = notificationService;
        this.libraryProperties = libraryProperties;
        System.out.println("LibraryService initialized");
        System.out.println("Library Name: " + libraryProperties.getName());
        System.out.println("Max books: " + libraryProperties.getMaxBooks());
    }

    public void addBook(String title, String author, String isbn) {
        Book book = new Book(null, title, author, isbn);
        bookRepository.save(book);
        notificationService.sendNotification("New book added: " + book.getTitle());
        System.out.println("✅ Book added: " + book.getTitle());
    }

    public void borrowBook(Long bookId, String memberName) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            System.out.println("❌ Book not found");
            return;
        }
        if (!book.isAvailable()){
            System.out.println("❌ Book is not available");
            return;
        }
        book.setAvailable(false);
        bookRepository.save(book);
        notificationService.sendNotification(String.format("Book %s borrowed by %s", book.getTitle(), memberName));
        System.out.printf("\n✅ %s borrowed: %s%n", memberName, book.getTitle());
    }

    public void returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            System.out.println("❌ Book not found");
            return;
        }
        book.setAvailable(true);
        bookRepository.save(book);
        notificationService.sendNotification(String.format("Book returned: %s", book.getTitle()));
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findAvailable();
    }
}
