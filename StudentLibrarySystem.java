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
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");

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
                    // Option 1: Add Book (Executed via AddBook class)
                    AddBook.execute(books, sc);
                    break;

                case 2:
                    // Option 2: Display Books (Executed via DisplayBooks class)
                    DisplayBooks.execute(books);
                    break;

                case 3:
                    // Option 3: Search Book (Execution handled / disabled in SearchBook class)
                    SearchBook.execute(books, sc);
                    break;

                case 4:
                    // Option 4: Issue Book (Execution handled / disabled in IssueBook class)
                    IssueBook.execute(books, sc);
                    break;

                case 5:
                    // Option 5: Return Book (Execution handled / disabled in ReturnBook class)
                    ReturnBook.execute(books, sc);
                    break;

                case 6:
                    // Option 6: Delete Book (Execution handled / disabled in DeleteBook class)
                    DeleteBook.execute(books, sc);
                    break;

                case 7:
                    // Option 7: Exit Application (Handled via ExitOption class)
                    ExitOption.execute();
                    break;

                default:
                    System.out.println(
                        "\nInvalid choice! Please try again."
                    );
            }

        } while (choice != 7);

        sc.close();
    }
}
