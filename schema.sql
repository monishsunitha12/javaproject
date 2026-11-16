-- ===================================================
-- Database & Table Creation Script for MSSQL Server
-- Database Name: library
-- Table Name: books
-- ===================================================

-- 1. Create the database 'library' if it does not already exist
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'library')
BEGIN
    CREATE DATABASE library;
    PRINT 'Database [library] created successfully.';
END
ELSE
BEGIN
    PRINT 'Database [library] already exists.';
END
GO

-- 2. Switch context to the 'library' database
USE library;
GO

-- 3. Create the 'books' table if it does not already exist
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[books]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[books] (
        [bookId]   INT PRIMARY KEY,
        [bookName] NVARCHAR(255) NOT NULL,
        [author]   NVARCHAR(255) NOT NULL,
        [issued]   BIT NOT NULL DEFAULT 0
    );
    PRINT 'Table [books] created successfully.';

    -- 4. Seed initial sample books
    INSERT INTO [dbo].[books] ([bookId], [bookName], [author], [issued]) VALUES
        (101, N'Java Programming', N'James Gosling', 0),
        (102, N'Python Programming', N'Guido van Rossum', 0),
        (103, N'Data Structures', N'Mark Allen Weiss', 0),
        (104, N'General Science', N'R.K. Sharma', 0),
        (105, N'Mathematics', N'R.D. Sharma', 0),
        (106, N'Physics', N'H.C. Verma', 0),
        (107, N'Botany', N'B.P. Pandey', 0),
        (108, N'Chemistry', N'P. Bahadur', 0),
        (109, N'Biology', N'Trueman', 0),
        (110, N'Computer Science', N'Sanjay Kumar', 0),
        (111, N'English Literature', N'R.K. Narayan', 0),
        (112, N'History', N'R.S. Sharma', 0),
        (113, N'Geography', N'Majid Hussain', 0),
        (114, N'Environmental Science', N'Erach Bharucha', 0),
        (115, N'Zoology', N'S.S. Lal', 0);
    PRINT 'Initial 15 books inserted successfully.';
END
ELSE
BEGIN
    PRINT 'Table [books] already exists.';
END
GO
