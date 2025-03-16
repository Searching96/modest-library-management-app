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

CREATE TABLE IF NOT EXISTS books
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    publication_year INT,
    genre VARCHAR(100),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    INDEX idx_deleted_at (deleted_at),
	CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO authors (author_name, birth_year) VALUES
('Donald Knuth', 1942);

INSERT INTO authors (author_name, birth_year) VALUES
('Ian Somerville', 1957);

INSERT INTO books (title, author_id, isbn, publication_year, genre) VALUES
('The Hitchhiker''s Guide to the Galaxy', 1, '978-0345391803', 1979, 'Science Fiction');

INSERT INTO books (title, author_id, isbn, publication_year, genre) VALUES
('Pride and Prejudice', 2, '978-0141439518', 1813, 'Classic');

INSERT INTO books (title, author_id, isbn, publication_year, genre) VALUES
('1984', 2, '978-0451524935', 1949, 'Dystopian');

INSERT INTO books (title, author_id, isbn, publication_year, genre) VALUES
('To Kill a Mockingbird', 1, '978-0061120084', 1960, 'Classic');

INSERT INTO books (title, author_id, isbn, publication_year, genre) VALUES
('Dune', 1, '978-0441172719', 1965, 'Science Fiction');

SELECT * FROM books;

SELECT * FROM authors;

SHOW CREATE TABLE books;

