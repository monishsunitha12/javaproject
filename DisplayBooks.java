import java.util.ArrayList;

// Option 2: Display Books (Executable)
public class DisplayBooks {

    public static final boolean IS_EXECUTABLE = true;

    public static void execute(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("\nNo books available.");
            return;
        }

        System.out.println("\n===== BOOK LIST =====");
        for (Book book : books) {
            book.displayBook();
        }
    }
}
