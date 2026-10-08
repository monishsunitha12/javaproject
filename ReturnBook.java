import java.util.ArrayList;
import java.util.Scanner;

// Option 5: Return Book (Execution Disabled per requirement)
public class ReturnBook {

    // Only Option 1 and Option 2 are permitted to be executed
    public static final boolean IS_EXECUTABLE = false;

    public static void execute(ArrayList<Book> books, Scanner sc) {
        if (!IS_EXECUTABLE) {
            System.out.println("\n[Action Disabled] Return Book cannot be executed.");
            System.out.println("Notice: Only the first two options (1. Add Book and 2. Display Books) are executable.");
            return;
        }

        // Logic preserved below
        returnBookLogic(books, sc);
    }

    public static void returnBookLogic(ArrayList<Book> books, Scanner sc) {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        for (Book book : books) {
            if (book.bookId == id) {
                if (book.issued) {
                    book.issued = false;
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }

        System.out.println("Book not found!");
    }
}
