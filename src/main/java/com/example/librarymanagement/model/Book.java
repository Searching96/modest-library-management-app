package com.example.librarymanagement.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "books", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"isbn"})
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private int publicationYear;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookGenre> bookGenres;

    // Constructors, getters, setters
    public Book() {
    }

    public Book(String title, Author author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<BookGenre> getBookGenres() {
        return bookGenres;
    }

    public void setBookGenres(List<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
    }
}
