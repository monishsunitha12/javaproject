# Student Library System (Modular Java Project)

A modular, console-based Java project implementing the **Student Library System**. The system is organized with each active feature in its own dedicated Java file.

---

## 📁 Project Structure

```
C:\javaproject
├── Book.java                   # Book entity model (attributes, constructor, display)
├── AddBook.java                # Option 1: Add a new book (Executable)
├── DisplayBooks.java           # Option 2: Display all books (Executable)
├── StudentLibrarySystem.java   # Main class containing menu and application entry point
├── .gitignore                  # Git ignore rules (ignores .class files)
└── README.md                   # Project documentation & GitHub push guide
```

---

## ⚙️ Active Operations

- **Option 1 (`AddBook.java`)**: Prompts user for Book ID, Title, and Author, then adds the book to the library.
- **Option 2 (`DisplayBooks.java`)**: Lists all available books and their current status.
- **Option 3 (`Exit`)**: Exits the application cleanly.

*(Additional features can be added in modular files in the future as needed).*

---

## 🚀 How to Compile and Run

Open PowerShell / Command Prompt inside `C:\javaproject`:

```powershell
# 1. Compile all Java files
javac *.java

# 2. Run the main application
java StudentLibrarySystem
```

---

## 🐙 Repository

- **GitHub Repository**: [https://github.com/monishsunitha12/javaproject](https://github.com/monishsunitha12/javaproject)
