-- Tạo database
CREATE DATABASE library;

-- Tạo bảng Authors
CREATE TABLE Authors (
                         author_id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         birth_year INT,
                         nationality VARCHAR(50)
);

-- Tạo bảng Categories
CREATE TABLE Categories (
                            category_id SERIAL PRIMARY KEY,
                            category_name VARCHAR(50) NOT NULL UNIQUE
);

-- Tạo bảng Books
CREATE TABLE Books (
                       book_id SERIAL PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       author_id INT REFERENCES Authors(author_id),
                       category_id INT REFERENCES Categories(category_id),
                       published_year INT,
                       total_copies INT DEFAULT 1,
                       available_copies INT DEFAULT 1
);

-- Tạo bảng Users
CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       phone_number VARCHAR(15),
                       address TEXT
);

-- Tạo bảng Borrowings
CREATE TABLE Borrowings (
                            borrowing_id SERIAL PRIMARY KEY,
                            user_id INT REFERENCES Users(user_id),
                            book_id INT REFERENCES Books(book_id),
                            borrowed_date DATE DEFAULT CURRENT_DATE,
                            due_date DATE,
                            returned_date DATE
);

-- Thêm dữ liệu vào bảng Authors
INSERT INTO Authors (name, birth_year, nationality)
VALUES
    ('J.K. Rowling', 1965, 'British'),
    ('George Orwell', 1903, 'British'),
    ('Haruki Murakami', 1949, 'Japanese');

-- Thêm dữ liệu vào bảng Categories
INSERT INTO Categories (category_name)
VALUES
    ('Fantasy'),
    ('Dystopian'),
    ('Literary Fiction');

-- Thêm dữ liệu vào bảng Books
INSERT INTO Books (title, author_id, category_id, published_year, total_copies, available_copies)
VALUES
    ('Harry Potter and the Philosopher Stone', 1, 1, 1997, 10, 10),
    ('1984', 2, 2, 1949, 5, 5),
    ('Kafka on the Shore', 3, 3, 2002, 7, 7);

-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (name, email, phone_number, address)
VALUES
    ('Alice Johnson', 'alice@example.com', '123456789', '123 Main St'),
    ('Bob Smith', 'bob@example.com', '987654321', '456 Elm St');

-- Thêm dữ liệu vào bảng Borrowings
INSERT INTO Borrowings (user_id, book_id, due_date)
VALUES
    (1, 1, '2024-01-10'),
    (2, 2, '2024-01-15');