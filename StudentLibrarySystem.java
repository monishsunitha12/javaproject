import java.util.ArrayList;
import java.util.Scanner;

// Main class
public class StudentLibrarySystem {

    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Main method
    public static void main(String[] args) {

        int choice;

        // Sample books
        books.add(new Book(101, "Java Programming", "James Gosling"));
        books.add(new Book(102, "Python Programming", "Guido van Rossum"));
        books.add(new Book(103, "Data Structures", "Mark Allen Weiss"));
        books.add(new Book(104, "General Science", "R.K. Sharma"));
        books.add(new Book(105, "Mathematics", "R.D. Sharma"));
        books.add(new Book(106, "Physics", "H.C. Verma"));
        books.add(new Book(107, "Botany", "B.P. Pandey"));
        books.add(new Book(108, "Chemistry", "P. Bahadur"));
        books.add(new Book(109, "Biology", "Trueman"));
        books.add(new Book(110, "Computer Science", "Sanjay Kumar"));
        books.add(new Book(111, "English Literature", "R.K. Narayan"));
        books.add(new Book(112, "History", "R.S. Sharma"));
        books.add(new Book(113, "Geography", "Majid Hussain"));
        books.add(new Book(114, "Environmental Science", "Erach Bharucha"));
        books.add(new Book(115, "Zoology", "S.S. Lal"));

        do {

            System.out.println("\n=================================");
            System.out.println("      STUDENT LIBRARY SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Exit");

            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("\nInvalid choice! Please enter a number.");
                sc.nextLine();
                choice = 0;
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AddBook.execute(books, sc);
                    break;

                case 2:
                    DisplayBooks.execute(books);
                    break;

                case 3:
                    System.out.println(
                        "\nThank you for using Student Library System!"
                    );
                    break;

                default:
                    System.out.println(
                        "\nInvalid choice! Please try again."
                    );
            }

        } while (choice != 3);

        sc.close();
    }
}
