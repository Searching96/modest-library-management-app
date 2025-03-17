package com.example.librarymanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genreName;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<BookGenre> bookGenres;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<BookGenre> getBookGenres() {
        return bookGenres;
    }

    public void setBookGenres(List<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
    }
}
