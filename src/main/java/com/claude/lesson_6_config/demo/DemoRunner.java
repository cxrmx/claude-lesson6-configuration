package com.claude.lesson_6_config.demo;

import com.claude.lesson_6_config.config.LibraryProperties;
import com.claude.lesson_6_config.models.Book;
import com.claude.lesson_6_config.services.LibraryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DemoRunner {
    private LibraryService libraryService;
    private LibraryProperties libraryProperties;
    private Environment environment;
    private String activeProfile;

    public DemoRunner(LibraryService libraryService, LibraryProperties libraryProperties, Environment environment,
                      @Value("${spring.profiles.active}") String activeProfile) {
        this.libraryService = libraryService;
        this.libraryProperties = libraryProperties;
        this.environment = environment;
        this.activeProfile = activeProfile;
    }

    public void runDemo() {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
    }

    private void demo1() {
        printSection("DEMO 1: CONFIGURATION INFO");
        System.out.println("Active profile: " + activeProfile);
        System.out.println();
        System.out.println("Library name: " + libraryProperties.getName());
        System.out.println("Max books: " + libraryProperties.getMaxBooks());
        System.out.println("Address: " + libraryProperties.getAddress());
        System.out.println("Weekdays work hours: " + libraryProperties.getOpeningHours().getWeekdays());
        System.out.println("Weekends work hours: " + libraryProperties.getOpeningHours().getWeekends());
        System.out.println();
        System.out.println("Active profiles from environment: " + Arrays.toString(environment.getActiveProfiles()));
    }

    private void demo2() {
        printSection("DEMO 2: ADDING BOOKS");
        if (activeProfile.equals("dev")) {
            System.out.println("Books already downloaded from test data");
        } else {
            libraryService.addBook("Design Patterns", "Gang of Four", "ISBN-101");
            libraryService.addBook("Refactoring", "Martin Fowler", "ISBN-102");
            libraryService.addBook("Domain-Driven Design", "Eric Evans", "ISBN-103");
        }
    }

    private void demo3() {
        printSection("DEMO 3: ALL BOOKS IN LIBRARY");
        List<Book> books = libraryService.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("Library has no books");
            return;
        } else {
            for (Book book : books) {
                System.out.printf(
                        "\n%d - %s by %s (Available: %b)%n", book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable()
                );
            }
        }
    }

    private void demo4() {
        printSection("DEMO 4: BORROWING BOOKS");
        if (activeProfile.equals("dev")) {
            libraryService.borrowBook(1L, "Alice");
            libraryService.borrowBook(2L, "Bob");
        } else {
            System.out.println("Simulation of borrowing is skipped for prod profile");
        }
    }

    private void demo5() {
        printSection("DEMO 5: AVAILABLE BOOKS");
        List<Book> books = libraryService.listAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("Library is empty");
            return;
        }
        System.out.println("Amount books in library: " + books.size());
        for (Book book : books) {
            System.out.printf("\n✅ %s by %s", book.getTitle(), book.getAuthor());
        }
    }

    private void demo6() {
        printSection("DEMO 6: RETURNING BOOKS");
        if (activeProfile.equals("dev")) {
            libraryService.returnBook(1L);
        } else {
            System.out.println("Simulation of returning is skipped for prod profile");
        }
    }

    private void demo7() {
        printSection("DEMO 7: FINAL STATISTICS");
        List<Book> allBooks = libraryService.listAllBooks();
        List<Book> availableBooks = libraryService.listAvailableBooks();
        Integer borrowedBooks = allBooks.size() - availableBooks.size();
        System.out.println("Amount of books in library: " + allBooks.size());
        System.out.println("Amount of available books in library: " + availableBooks.size());
        System.out.println("Amount of borrowed books: " + borrowedBooks);
        System.out.println("Active profile: " + activeProfile);
    }

    private void printSection(String title) {
        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println(title);
        System.out.println("=".repeat(60));
        System.out.println();
    }
}
