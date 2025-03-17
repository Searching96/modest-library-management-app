package com.example.librarymanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book_genres", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"book_id", "genre_id"})
})
public class BookGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
    
    public BookGenre() {
    }

    public BookGenre(Book book, Genre genre) {
        this.book = book;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
