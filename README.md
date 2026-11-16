# Student Library System (Modular Java Project with Real-Time Database Persistence)

A modular, console-based Java application implementing the **Student Library System** with real-time, permanent data persistence powered by a local database (`library.db`).

---

## 📁 Project Structure

```
C:\javaproject
├── Book.java                   # Entity model for Book records (No arrays)
├── AddBook.java                # Option 1: Add a new book (Real-time SQL INSERT into database)
├── DisplayBooks.java           # Option 2: Display all books (Real-time SQL SELECT from database)
├── DatabaseConnection.java     # JDBC connection manager & automatic schema initializer (No arrays)
├── StudentLibrarySystem.java   # Main application driver & interactive menu controller
├── db.properties               # Database configuration (db.url=jdbc:sqlite:library.db)
├── schema.sql                  # Database table schema and initial seed SQL script
├── lib/
│   └── sqlite-jdbc-3.49.1.0.jar # Self-contained JDBC Driver for the library database
├── .gitignore                  # Git ignore rules (.class, *.db, IDE files, etc.)
└── README.md                   # Project documentation & execution guide
```

---

## 🗄️ Database Architecture & Storage

- **Database Name**: `library` (`library.db`)
- **Table Name**: `books`
- **Zero Arrays Policy**: Arrays are **not** used to store, manage, or transfer data. Every operation directly queries and modifies the database in real time.
- **Table Schema**:
  - `bookId INTEGER PRIMARY KEY`: Unique numeric primary key enforcing book uniqueness.
  - `bookName TEXT NOT NULL`: Book title.
  - `author TEXT NOT NULL`: Author name.
  - `issued INTEGER NOT NULL DEFAULT 0`: Availability flag (0 = Available, 1 = Issued).

---

## ⚙️ Active Operations

- **Option 1 (`AddBook.java`)**: Prompts user for Book ID, Title, and Author, checks for duplicate IDs, and executes an `INSERT INTO books` query to permanently store the book in the database in real time.
- **Option 2 (`DisplayBooks.java`)**: Executes `SELECT * FROM books ORDER BY bookId ASC` and displays books directly from the database in real time.
- **Option 3 (`Exit`)**: Terminates the application cleanly.

---

## 🚀 How to Compile and Run

Open PowerShell inside `C:\javaproject`:

```powershell
# 1. Compile all Java files with the JDBC driver in classpath
javac -cp ".;lib/*" *.java

# 2. Run the application
java -cp ".;lib/*" StudentLibrarySystem
```

---

## 🐙 Repository

- **GitHub Repository**: [https://github.com/monishsunitha12/javaproject](https://github.com/monishsunitha12/javaproject)
