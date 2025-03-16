CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE IF NOT EXISTS books
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    publication_year INT,
    genre VARCHAR(100),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	deleted_at TIMESTAMP NULL DEFAULT NULL,
    INDEX idx_deleted_at (deleted_at)
);

INSERT INTO books (title, author, isbn, publication_year, genre) VALUES
('The Hitchhiker''s Guide to the Galaxy', 'Douglas Adams', '978-0345391803', 1979, 'Science Fiction');

INSERT INTO books (title, author, isbn, publication_year, genre) VALUES
('Pride and Prejudice', 'Jane Austen', '978-0141439518', 1813, 'Classic');

INSERT INTO books (title, author, isbn, publication_year, genre) VALUES
('1984', 'George Orwell', '978-0451524935', 1949, 'Dystopian');

INSERT INTO books (title, author, isbn, publication_year, genre) VALUES
('To Kill a Mockingbird', 'Harper Lee', '978-0061120084', 1960, 'Classic');

INSERT INTO books (title, author, isbn, publication_year, genre) VALUES
('Dune', 'Frank Herbert', '978-0441172719', 1965, 'Science Fiction');

select * from books;

show create table books;

