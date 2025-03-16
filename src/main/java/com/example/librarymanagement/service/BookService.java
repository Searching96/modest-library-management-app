package com.example.librarymanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        // try {
        //     List<Book> books = bookRepository.findAll();
        //     logger.info("Books fetched from database: " + books.size());
        //     return books;
        // } catch (Exception e) {
        //     logger.error("Error fetching books from database", e);
        //     throw e;
        // }
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    public void editBook(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setGenre(updatedBook.getGenre());
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        bookRepository.delete(book);
    }
}
