import java.util.ArrayList;
import java.util.Scanner;

// Option 4: Issue Book (Execution Disabled per requirement)
public class IssueBook {

    // Only Option 1 and Option 2 are permitted to be executed
    public static final boolean IS_EXECUTABLE = false;

    public static void execute(ArrayList<Book> books, Scanner sc) {
        if (!IS_EXECUTABLE) {
            System.out.println("\n[Action Disabled] Issue Book cannot be executed.");
            System.out.println("Notice: Only the first two options (1. Add Book and 2. Display Books) are executable.");
            return;
        }

        // Logic preserved below
        issueBookLogic(books, sc);
    }

    public static void issueBookLogic(ArrayList<Book> books, Scanner sc) {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        for (Book book : books) {
            if (book.bookId == id) {
                if (!book.issued) {
                    book.issued = true;
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }

        System.out.println("Book not found!");
    }
}
