import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Option 2: Display Books (Executable with MSSQL Database Persistence)
public class DisplayBooks {

    public static final boolean IS_EXECUTABLE = true;

    public static void execute() {
        String query = "SELECT bookId, bookName, author, issued FROM books ORDER BY bookId ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            boolean hasBooks = false;

            while (rs.next()) {
                if (!hasBooks) {
                    System.out.println("\n===== BOOK LIST (MSSQL DATABASE) =====");
                    hasBooks = true;
                }

                int id = rs.getInt("bookId");
                String name = rs.getString("bookName");
                String author = rs.getString("author");
                boolean issued = rs.getBoolean("issued");

                Book book = new Book(id, name, author, issued);
                book.displayBook();
            }

            if (!hasBooks) {
                System.out.println("\nNo books available in the database.");
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Failed to fetch books from MSSQL: " + e.getMessage());
            System.err.println("Please verify your MSSQL Server status and 'db.properties'.");
        }
    }
}
