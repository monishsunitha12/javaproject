# Student Library System (Modular Java Project)

A modular, console-based Java project implementing the **Student Library System**. The system has been decomposed into individual Java source files for each entity and menu operation.

---

## 📁 Project Structure

```
C:\javaproject
├── Book.java                   # Book entity model (attributes, constructor, display)
├── AddBook.java                # Option 1: Add a new book (Executable)
├── DisplayBooks.java           # Option 2: Display all books (Executable)
├── SearchBook.java             # Option 3: Search book by ID (Disabled)
├── IssueBook.java              # Option 4: Issue book by ID (Disabled)
├── ReturnBook.java             # Option 5: Return book by ID (Disabled)
├── DeleteBook.java             # Option 6: Delete book by ID (Disabled)
├── ExitOption.java             # Option 7: Exit system handler
├── StudentLibrarySystem.java   # Main class containing menu and application entry point
├── .gitignore                  # Git ignore rules (ignores .class files)
└── README.md                   # Project documentation & GitHub push guide
```

---

## ⚙️ Execution Rules

As requested:
- **Option 1 (`AddBook.java`)**: ✅ **Executable**
- **Option 2 (`DisplayBooks.java`)**: ✅ **Executable**
- **Options 3–6 (`SearchBook.java`, `IssueBook.java`, `ReturnBook.java`, `DeleteBook.java`)**: 🚫 **Disabled** (Their complete algorithms are preserved in each file, but guarded to prevent execution).

---

## 🚀 How to Compile and Run

Open PowerShell / Terminal inside `C:\javaproject`:

```powershell
# 1. Compile all Java files
javac *.java

# 2. Run the main class
java StudentLibrarySystem
```

---

## 🐙 How to Push this Project to GitHub from VS Code

The repository has been initialized with Git and committed locally. Follow these steps in **VS Code**:

1. **Open the project in VS Code**:
   ```powershell
   code C:\javaproject
   ```
2. **Go to Source Control**:
   - Press `Ctrl + Shift + G` (or click the Source Control icon on the left activity bar).
3. **Publish to GitHub**:
   - Click the blue **"Publish to GitHub"** button.
   - VS Code will prompt you:
     - Choose either **"Publish to GitHub public repository"** or **"Publish to GitHub private repository"**.
   - If prompted, sign in to your GitHub account in the browser to authorize VS Code.
4. **Done!** VS Code will create the repository on your GitHub account and push all files automatically.
