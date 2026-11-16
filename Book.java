public class Book {

    public int bookId;
    public String bookName;
    public String author;
    public boolean issued;

    // Constructor for new books (default issued = false)
    public Book(int bookId, String bookName, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.issued = false;
    }

    // Overloaded Constructor for books loaded from database
    public Book(int bookId, String bookName, String author, boolean issued) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.issued = issued;
    }

    // Display book details
    public void displayBook() {
        System.out.println("Book ID     : " + bookId);
        System.out.println("Book Name   : " + bookName);
        System.out.println("Author      : " + author);

        if (issued) {
            System.out.println("Status      : Issued");
        } else {
            System.out.println("Status      : Available");
        }

        System.out.println("------------------------------");
    }
}
