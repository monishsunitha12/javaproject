import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Option 2: Display Books (Executable - Real-time Database Access)
public class DisplayBooks {

    public static final boolean IS_EXECUTABLE = true;

    public static void execute() {
        String query = "SELECT bookId, bookName, author, issued FROM books ORDER BY bookId ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            boolean hasBooks = false;

            // Fetch and render each record directly from the database in real time (NO ARRAYS)
            while (rs.next()) {
                if (!hasBooks) {
                    System.out.println("\n===== BOOK LIST (LIBRARY DATABASE) =====");
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
                System.out.println("\nNo books available in the database.");
            }

        } catch (SQLException e) {
            System.err.println("\n[Database Error] Failed to fetch books from database: " + e.getMessage());
        }
    }
}
