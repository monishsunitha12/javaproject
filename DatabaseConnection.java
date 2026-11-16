import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// Database Connection & Real-Time Persistent Storage Manager
public class DatabaseConnection {

    private static String dbUrl = "jdbc:sqlite:library.db";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("db.properties")) {
            props.load(input);
            dbUrl = props.getProperty("db.url", dbUrl);
        } catch (IOException e) {
            // Keep default SQLite persistent database file
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl);
    }

    // Initialize database table 'books' and seed initial records directly into DB (NO ARRAYS)
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

            // Check if table is empty; if so, populate initial records directly via SQL
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
            System.err.println("\n[Database Error] Initialization failed: " + e.getMessage());
        }
    }
}
