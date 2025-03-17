package com.example.librarymanagement.service;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.BookGenre;
import com.example.librarymanagement.model.Genre;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    // private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
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
        List<BookGenre> newBookGenres = new ArrayList<>();

        if (book.getGenre() != null && !book.getGenre().isEmpty()) {
            String[] genreNames = book.getGenre().split(",");
            for (String rawName : genreNames) {
                String name = rawName.trim();
                Genre found = genreRepository.findByGenreName(name);
                if (found == null) {
                    found = new Genre(name);
                    genreRepository.save(found);
                }
                BookGenre bg = new BookGenre(book, found);
                newBookGenres.add(bg);
            }
        }

        book.setBookGenres(newBookGenres);
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
        book.setBookGenres(updatedBook.getBookGenres());
        //book.setGenre(updatedBook.getGenre());
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    public String getFormattedGenres(Book book) {
        return book.getBookGenres().stream()
            .map(BookGenre::getGenre)
            .map(genre -> genre.getGenreName())
            .collect(Collectors.joining(", "));
    }
}
