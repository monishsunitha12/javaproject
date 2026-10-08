import java.util.ArrayList;
import java.util.Scanner;

// Option 1: Add Book (Executable)
public class AddBook {

    public static final boolean IS_EXECUTABLE = true;

    public static void execute(ArrayList<Book> books, Scanner sc) {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        Book book = new Book(id, name, author);
        books.add(book);

        System.out.println("\nBook added successfully!");
    }
}
