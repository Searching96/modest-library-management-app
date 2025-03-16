package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.service.AuthorService;
import com.example.librarymanagement.service.BookService;

import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    // private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        // logger.info("Fetching all books");
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        // logger.info("Books fetched: " + books.size());
        return "book-list"; // Points to book-list.html
    }

    @GetMapping("/books/new")
    public String showAddBookForm(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        return "add-book"; // Points to add-book.html
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        // logger.info("Received request for editing book with id: " + id);
        Book book = bookService.findBookById(id);
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "edit-book"; // Points to edit-book.html
    }

    @PostMapping("/books/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        bookService.editBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
