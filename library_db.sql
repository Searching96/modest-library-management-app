# USE mysql; 
DROP DATABASE IF EXISTS library_db;

CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE IF NOT EXISTS authors
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL,
    birth_year INT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    INDEX idx_deleted_at (deleted_at)
);

CREATE TABLE IF NOT EXISTS genres
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    genre_name VARCHAR(255) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    INDEX idx_deleted_at (deleted_at)
);

CREATE TABLE IF NOT EXISTS books
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    publication_year INT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    INDEX idx_deleted_at (deleted_at),
	CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE IF NOT EXISTS books_genres
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    genre_id INT NOT NULL,
    CONSTRAINT fk_books_genres_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    CONSTRAINT fk_books_genres_genre FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE,
    UNIQUE (book_id, genre_id)
);

INSERT INTO authors (author_name, birth_year) VALUES
('Donald Knuth', 1942);

INSERT INTO authors (author_name, birth_year) VALUES
('Ian Somerville', 1957);

INSERT INTO genres (genre_name) VALUES
('Extreme Sport');

INSERT INTO genres (genre_name) VALUES
('Extreme Subject');

INSERT INTO genres (genre_name) VALUES
('Science Fiction');

INSERT INTO books (title, author_id, isbn, publication_year) VALUES
('The Hitchhiker''s Guide to the Galaxy', 1, '978-0345391803', 1979);

INSERT INTO books (title, author_id, isbn, publication_year) VALUES
('Pride and Prejudice', 2, '978-0141439518', 1813);

INSERT INTO books (title, author_id, isbn, publication_year) VALUES
('1984', 2, '978-0451524935', 1949);

INSERT INTO books (title, author_id, isbn, publication_year) VALUES
('To Kill a Mockingbird', 1, '978-0061120084', 1960);

INSERT INTO books (title, author_id, isbn, publication_year) VALUES
('Dune', 1, '978-0441172719', 1965);

INSERT INTO books_genres (book_id, genre_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 1),
(4, 3),
(5, 1),
(5, 2);

SELECT * FROM books;
SELECT * FROM authors;
SELECT * FROM books_genres;
SHOW CREATE TABLE books;
SHOW CREATE TABLE authors;
SHOW CREATE TABLE genres;
SHOW CREATE TABLE books_genres;