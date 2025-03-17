package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.service.AuthorService;
import com.example.librarymanagement.service.BookService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        try {
            List<Book> books = bookService.findAllBooks();
            List<String> formattedGenresList = new ArrayList<>();
            
            for (Book book : books) {
                String genres = bookService.getFormattedGenres(book);
                formattedGenresList.add(genres);
            }
            
            model.addAttribute("books", books);
            model.addAttribute("formattedGenresList", formattedGenresList);
            return "book-list";
        } catch (Exception e) {
            // Log the error or handle it appropriately
            return "error";
        }
    }

    @GetMapping("/books/new")
    public String showAddBookForm(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        return "add-book";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBookById(id);
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "edit-book";
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
