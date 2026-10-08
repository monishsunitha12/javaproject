import java.util.ArrayList;
import java.util.Scanner;

// Option 6: Delete Book (Execution Disabled per requirement)
public class DeleteBook {

    // Only Option 1 and Option 2 are permitted to be executed
    public static final boolean IS_EXECUTABLE = false;

    public static void execute(ArrayList<Book> books, Scanner sc) {
        if (!IS_EXECUTABLE) {
            System.out.println("\n[Action Disabled] Delete Book cannot be executed.");
            System.out.println("Notice: Only the first two options (1. Add Book and 2. Display Books) are executable.");
            return;
        }

        // Logic preserved below
        deleteBookLogic(books, sc);
    }

    public static void deleteBookLogic(ArrayList<Book> books, Scanner sc) {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();

        for (Book book : books) {
            if (book.bookId == id) {
                books.remove(book);
                System.out.println("Book deleted successfully!");
                return;
            }
        }

        System.out.println("Book not found!");
    }
}
