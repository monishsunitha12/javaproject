import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// Option 1: Add Book (Executable - Real-time Database Persistent Storage)
public class AddBook {

    public static final boolean IS_EXECUTABLE = true;

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
            // Check for duplicate ID in database
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

                System.out.println("\nBook added successfully and saved permanently to library database!");
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Failed to save book to database: " + e.getMessage());
        }
    }
}
