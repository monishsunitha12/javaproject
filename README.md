# Student Library System (Modular Java Project with MSSQL Server Persistence)

A modular, console-based Java application implementing the **Student Library System** with permanent data persistence powered by **Microsoft SQL Server (MSSQL)**.

---

## 📁 Project Structure

```
C:\javaproject
├── Book.java                   # Entity model for Book records
├── AddBook.java                # Option 1: Add a new book (INSERT into MSSQL database)
├── DisplayBooks.java           # Option 2: Display all books (SELECT from MSSQL database)
├── DatabaseConnection.java     # JDBC connection manager & automatic schema initializer
├── StudentLibrarySystem.java   # Main application driver & interactive menu controller
├── db.properties               # MSSQL configuration (host, port, db, user, password)
├── schema.sql                  # Database (library) and table (books) DDL/DML script
├── lib/
│   └── mssql-jdbc-12.8.1.jre11.jar # Official Microsoft JDBC Driver for SQL Server
├── .gitignore                  # Git ignore rules (.class, IDE files, etc.)
└── README.md                   # Project documentation & execution guide
```

---

## 🗄️ Database Setup (MSSQL Server)

### 1. Database & Table Details
- **Database Name**: `library`
- **Table Name**: `books`
- **Schema**:
  - `bookId INT PRIMARY KEY`
  - `bookName NVARCHAR(255) NOT NULL`
  - `author NVARCHAR(255) NOT NULL`
  - `issued BIT NOT NULL DEFAULT 0`

You can execute [`schema.sql`](file:///C:/javaproject/schema.sql) in SQL Server Management Studio (SSMS) or Azure Data Studio, **OR** let the Java application automatically create the table and seed the initial 15 books on startup!

### 2. Configure Credentials in `db.properties`
Open [`db.properties`](file:///C:/javaproject/db.properties) and update with your MSSQL instance details:
```properties
db.host=localhost
db.port=1433
db.name=library
db.user=sa
db.password=YourPassword123!
db.encrypt=false
db.trustServerCertificate=true
db.integratedSecurity=false
```

---

## ⚙️ Active Operations

- **Option 1 (`AddBook.java`)**: Prompts user for Book ID, Title, and Author, then executes an `INSERT INTO books` query to permanently store the book in MSSQL.
- **Option 2 (`DisplayBooks.java`)**: Executes `SELECT * FROM books ORDER BY bookId ASC` and renders all books from the database.
- **Option 3 (`Exit`)**: Terminates the application cleanly.

---

## 🚀 How to Compile and Run

Open PowerShell inside `C:\javaproject`:

```powershell
# 1. Compile all Java files including the MSSQL JDBC driver
javac -cp ".;lib/*" *.java

# 2. Run the application
java -cp ".;lib/*" StudentLibrarySystem
```

---

## 🐙 Repository

- **GitHub Repository**: [https://github.com/monishsunitha12/javaproject](https://github.com/monishsunitha12/javaproject)
