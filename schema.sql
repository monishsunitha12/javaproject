-- ===================================================
-- Database & Table Creation Script
-- Database: library
-- Table: books
-- ===================================================

-- Create table 'books' if it does not exist
CREATE TABLE IF NOT EXISTS books (
    bookId   INTEGER PRIMARY KEY,
    bookName TEXT NOT NULL,
    author   TEXT NOT NULL,
    issued   INTEGER NOT NULL DEFAULT 0
);

-- Seed initial books
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (101, 'Java Programming', 'James Gosling', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (102, 'Python Programming', 'Guido van Rossum', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (103, 'Data Structures', 'Mark Allen Weiss', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (104, 'General Science', 'R.K. Sharma', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (105, 'Mathematics', 'R.D. Sharma', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (106, 'Physics', 'H.C. Verma', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (107, 'Botany', 'B.P. Pandey', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (108, 'Chemistry', 'P. Bahadur', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (109, 'Biology', 'Trueman', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (110, 'Computer Science', 'Sanjay Kumar', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (111, 'English Literature', 'R.K. Narayan', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (112, 'History', 'R.S. Sharma', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (113, 'Geography', 'Majid Hussain', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (114, 'Environmental Science', 'Erach Bharucha', 0);
INSERT OR IGNORE INTO books (bookId, bookName, author, issued) VALUES (115, 'Zoology', 'S.S. Lal', 0);
