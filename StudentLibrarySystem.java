import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

// =========================================================================
// 1. Book Entity Model (Single Entity Representation - NO ARRAYS)
// =========================================================================
class Book {

    int bookId;
    String bookName;
    String author;
    boolean issued;

    // Constructor for new books
    Book(int bookId, String bookName, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.issued = false;
    }

    // Overloaded Constructor for database records
    Book(int bookId, String bookName, String author, boolean issued) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.issued = issued;
    }

    // Display book card to standard output
    void displayBook() {
        System.out.println("Book ID     : " + bookId);
        System.out.println("Book Name   : " + bookName);
        System.out.println("Author      : " + author);

        if (issued) {
            System.out.println("Status      : Issued");
        } else {
            System.out.println("Status      : Available");
        }

        System.out.println("------------------------------");
    }
}

// =========================================================================
// 2. JDBC Driver Shim (Enables dynamic driver registration in single-click VS Code run)
// =========================================================================
class DriverShim implements Driver {
    private final Driver driver;

    DriverShim(Driver d) {
        this.driver = d;
    }

    @Override
    public boolean acceptsURL(String u) throws SQLException {
        return this.driver.acceptsURL(u);
    }

    @Override
    public Connection connect(String u, Properties p) throws SQLException {
        return this.driver.connect(u, p);
    }

    @Override
    public int getMajorVersion() {
        return this.driver.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return this.driver.getMinorVersion();
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
        return this.driver.getPropertyInfo(u, p);
    }

    @Override
    public boolean jdbcCompliant() {
        return this.driver.jdbcCompliant();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this.driver.getParentLogger();
    }
}

// =========================================================================
// 3. Database Connection & Real-Time Persistent Storage (NO ARRAYS)
// =========================================================================
class DatabaseConnection {

    private static String dbUrl = "jdbc:sqlite:library.db";
    private static boolean driverLoaded = false;

    static {
        loadProperties();
        ensureDriver();
    }

    private static void loadProperties() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("db.properties")) {
            props.load(input);
            dbUrl = props.getProperty("db.url", dbUrl);
        } catch (IOException e) {
            // Default to library.db
        }
    }

    // Automatically loads SQLite driver even if VS Code executes without -cp lib/*
    private static synchronized void ensureDriver() {
        if (driverLoaded) return;

        try {
            Class.forName("org.sqlite.JDBC");
            driverLoaded = true;
            return;
        } catch (ClassNotFoundException e) {
            // Not on system classpath; locate the JAR file dynamically
        }

        String[] candidatePaths = {
            "lib/sqlite-jdbc-3.49.1.0.jar",
            "lib/sqlite-jdbc.jar",
            "c:/Users/monis/OneDrive/Desktop/java/javaproject/lib/sqlite-jdbc-3.49.1.0.jar",
            "C:/javaproject/lib/sqlite-jdbc-3.49.1.0.jar",
            "../lib/sqlite-jdbc-3.49.1.0.jar"
        };

        for (String p : candidatePaths) {
            File f = new File(p);
            if (f.exists()) {
                try {
                    URLClassLoader loader = new URLClassLoader(new URL[]{f.toURI().toURL()}, DatabaseConnection.class.getClassLoader());
                    Driver d = (Driver) Class.forName("org.sqlite.JDBC", true, loader).getDeclaredConstructor().newInstance();
                    DriverManager.registerDriver(new DriverShim(d));
                    driverLoaded = true;
                    return;
                } catch (Exception ex) {
                    // Try next path
                }
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        ensureDriver();
        return DriverManager.getConnection(dbUrl);
    }

    // Initialize database table 'books' and seed default records directly via SQL (NO ARRAYS)
    public static void initializeDatabase() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS books (" +
                "bookId INTEGER PRIMARY KEY, " +
                "bookName TEXT NOT NULL, " +
                "author TEXT NOT NULL, " +
                "issued INTEGER NOT NULL DEFAULT 0" +
                ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSql);

            // Check if table is empty; if so, populate default seed records directly via SQL
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (101, 'Java Programming', 'James Gosling', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (102, 'Python Programming', 'Guido van Rossum', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (103, 'Data Structures', 'Mark Allen Weiss', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (104, 'General Science', 'R.K. Sharma', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (105, 'Mathematics', 'R.D. Sharma', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (106, 'Physics', 'H.C. Verma', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (107, 'Botany', 'B.P. Pandey', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (108, 'Chemistry', 'P. Bahadur', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (109, 'Biology', 'Trueman', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (110, 'Computer Science', 'Sanjay Kumar', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (111, 'English Literature', 'R.K. Narayan', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (112, 'History', 'R.S. Sharma', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (113, 'Geography', 'Majid Hussain', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (114, 'Environmental Science', 'Erach Bharucha', 0)");
                stmt.executeUpdate("INSERT INTO books (bookId, bookName, author, issued) VALUES (115, 'Zoology', 'S.S. Lal', 0)");
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Notice] " + e.getMessage());
        }
    }
}

// =========================================================================
// 4. Option 1: Add Book (Direct SQL INSERT in Real Time - NO ARRAYS)
// =========================================================================
class AddBook {

    public static void execute(Scanner sc) {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        String checkSql = "SELECT COUNT(*) FROM books WHERE bookId = ?";
        String insertSql = "INSERT INTO books (bookId, bookName, author, issued) VALUES (?, ?, ?, 0)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check for duplicate ID directly via database query
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("\n[Error] Book with ID " + id + " already exists in the database!");
                    return;
                }
            }

            // Directly insert into the library database in real time (NO ARRAYS)
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setInt(1, id);
                insertStmt.setString(2, name);
                insertStmt.setString(3, author);
                insertStmt.executeUpdate();

                System.out.println("\nBook added successfully!");
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Failed to save book: " + e.getMessage());
        }
    }
}

// =========================================================================
// 5. Option 2: Display Books (Direct SQL SELECT in Real Time - NO ARRAYS)
// =========================================================================
class DisplayBooks {

    public static void execute() {
        String query = "SELECT bookId, bookName, author, issued FROM books ORDER BY bookId ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            boolean hasBooks = false;

            // Stream and render each book directly from database cursor (NO ARRAYS)
            while (rs.next()) {
                if (!hasBooks) {
                    System.out.println("\n===== BOOK LIST =====");
                    hasBooks = true;
                }

                int id = rs.getInt("bookId");
                String name = rs.getString("bookName");
                String author = rs.getString("author");
                boolean issued = (rs.getInt("issued") == 1);

                Book book = new Book(id, name, author, issued);
                book.displayBook();
            }

            if (!hasBooks) {
                System.out.println("\nNo books available.");
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Failed to fetch books: " + e.getMessage());
        }
    }
}

// =========================================================================
// 6. Option 3: Search Book (Direct SQL Query in Real Time - NO ARRAYS)
// =========================================================================
class SearchBook {

    public static void execute(Scanner sc) {
        System.out.print("Enter Book ID to search: ");
        int id = sc.nextInt();

        String query = "SELECT bookId, bookName, author, issued FROM books WHERE bookId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nBook Found");
                    int bookId = rs.getInt("bookId");
                    String name = rs.getString("bookName");
                    String author = rs.getString("author");
                    boolean issued = (rs.getInt("issued") == 1);

                    Book book = new Book(bookId, name, author, issued);
                    book.displayBook();
                } else {
                    System.out.println("Book not found!");
                }
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Search failed: " + e.getMessage());
        }
    }
}

// =========================================================================
// 7. Option 4: Issue Book (Direct SQL UPDATE in Real Time - NO ARRAYS)
// =========================================================================
class IssueBook {

    public static void execute(Scanner sc) {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        String selectSql = "SELECT issued FROM books WHERE bookId = ?";
        String updateSql = "UPDATE books SET issued = 1 WHERE bookId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    boolean issued = (rs.getInt("issued") == 1);
                    if (!issued) {
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                            updateStmt.setInt(1, id);
                            updateStmt.executeUpdate();
                            System.out.println("Book issued successfully!");
                        }
                    } else {
                        System.out.println("Book is already issued.");
                    }
                } else {
                    System.out.println("Book not found!");
                }
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Issue failed: " + e.getMessage());
        }
    }
}

// =========================================================================
// 8. Option 5: Return Book (Direct SQL UPDATE in Real Time - NO ARRAYS)
// =========================================================================
class ReturnBook {

    public static void execute(Scanner sc) {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        String selectSql = "SELECT issued FROM books WHERE bookId = ?";
        String updateSql = "UPDATE books SET issued = 0 WHERE bookId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    boolean issued = (rs.getInt("issued") == 1);
                    if (issued) {
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                            updateStmt.setInt(1, id);
                            updateStmt.executeUpdate();
                            System.out.println("Book returned successfully!");
                        }
                    } else {
                        System.out.println("Book was not issued.");
                    }
                } else {
                    System.out.println("Book not found!");
                }
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Return failed: " + e.getMessage());
        }
    }
}

// =========================================================================
// 9. Option 6: Delete Book (Direct SQL DELETE in Real Time - NO ARRAYS)
// =========================================================================
class DeleteBook {

    public static void execute(Scanner sc) {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();

        String selectSql = "SELECT COUNT(*) FROM books WHERE bookId = ?";
        String deleteSql = "DELETE FROM books WHERE bookId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setInt(1, id);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                        deleteStmt.setInt(1, id);
                        deleteStmt.executeUpdate();
                        System.out.println("Book deleted successfully!");
                    }
                } else {
                    System.out.println("Book not found!");
                }
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Delete failed: " + e.getMessage());
        }
    }
}

// =========================================================================
// 10. Main Class: StudentLibrarySystem (Full Interactive Menu - Single Click Ready)
// =========================================================================
public class StudentLibrarySystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        // Ensure database table 'books' exists and default books are seeded (NO ARRAYS)
        DatabaseConnection.initializeDatabase();

        do {

            System.out.println("\n=================================");
            System.out.println("      STUDENT LIBRARY SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");

            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("\nInvalid choice! Please try again.");
                sc.nextLine();
                choice = 0;
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AddBook.execute(sc);
                    break;

                case 2:
                    DisplayBooks.execute();
                    break;

                case 3:
                    SearchBook.execute(sc);
                    break;

                case 4:
                    IssueBook.execute(sc);
                    break;

                case 5:
                    ReturnBook.execute(sc);
                    break;

                case 6:
                    DeleteBook.execute(sc);
                    break;

                case 7:
                    System.out.println(
                        "\nThank you for using Student Library System!"
                    );
                    break;

                default:
                    System.out.println(
                        "\nInvalid choice! Please try again."
                    );
            }

        } while (choice != 7);

        sc.close();
    }
}
