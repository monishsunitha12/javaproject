import java.util.Scanner;

// Main class - Student Library System (with MSSQL Server Persistence)
public class StudentLibrarySystem {

    static Scanner sc = new Scanner(System.in);

    // Main method
    public static void main(String[] args) {

        int choice;

        System.out.println("Connecting to Microsoft SQL Server...");
        DatabaseConnection.initializeDatabase();

        do {

            System.out.println("\n=================================");
            System.out.println("      STUDENT LIBRARY SYSTEM");
            System.out.println("    (MSSQL Database Persistent)");
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
                    // Option 1: Add Book to MSSQL Database
                    AddBook.execute(sc);
                    break;

                case 2:
                    // Option 2: Display Books from MSSQL Database
                    DisplayBooks.execute();
                    break;

                case 3:
                    // Option 3: Exit
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
