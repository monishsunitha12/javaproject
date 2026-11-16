import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {

    private static String host = "localhost";
    private static String port = "1433";
    private static String dbName = "library";
    private static String user = "sa";
    private static String password = "YourPassword123!";
    private static String encrypt = "false";
    private static String trustServerCertificate = "true";
    private static boolean integratedSecurity = false;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("db.properties")) {
            props.load(input);
            host = props.getProperty("db.host", host);
            port = props.getProperty("db.port", port);
            dbName = props.getProperty("db.name", dbName);
            user = props.getProperty("db.user", user);
            password = props.getProperty("db.password", password);
            encrypt = props.getProperty("db.encrypt", encrypt);
            trustServerCertificate = props.getProperty("db.trustServerCertificate", trustServerCertificate);
            integratedSecurity = Boolean.parseBoolean(props.getProperty("db.integratedSecurity", "false"));
        } catch (IOException e) {
            System.out.println("[Notice] db.properties not found, using default MSSQL connection settings.");
        }
    }

    public static String getConnectionString() {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:sqlserver://").append(host).append(":").append(port);
        sb.append(";databaseName=").append(dbName);
        sb.append(";encrypt=").append(encrypt);
        sb.append(";trustServerCertificate=").append(trustServerCertificate);
        if (integratedSecurity) {
            sb.append(";integratedSecurity=true");
        }
        sb.append(";");
        return sb.toString();
    }

    public static Connection getConnection() throws SQLException {
        String url = getConnectionString();
        if (integratedSecurity) {
            return DriverManager.getConnection(url);
        } else {
            return DriverManager.getConnection(url, user, password);
        }
    }

    // Auto-create table and seed data if not present
    public static void initializeDatabase() {
        String checkTableSql = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'books') " +
                "BEGIN " +
                "    CREATE TABLE books ( " +
                "        bookId INT PRIMARY KEY, " +
                "        bookName NVARCHAR(255) NOT NULL, " +
                "        author NVARCHAR(255) NOT NULL, " +
                "        issued BIT NOT NULL DEFAULT 0 " +
                "    ); " +
                "END";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(checkTableSql);

            // Check if any books exist; if empty, seed initial books
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM books");
            if (rs.next() && rs.getInt(1) == 0) {
                String seedSql = "INSERT INTO books (bookId, bookName, author, issued) VALUES (?, ?, ?, 0)";
                try (PreparedStatement pstmt = conn.prepareStatement(seedSql)) {
                    Object[][] initialBooks = {
                        {101, "Java Programming", "James Gosling"},
                        {102, "Python Programming", "Guido van Rossum"},
                        {103, "Data Structures", "Mark Allen Weiss"},
                        {104, "General Science", "R.K. Sharma"},
                        {105, "Mathematics", "R.D. Sharma"},
                        {106, "Physics", "H.C. Verma"},
                        {107, "Botany", "B.P. Pandey"},
                        {108, "Chemistry", "P. Bahadur"},
                        {109, "Biology", "Trueman"},
                        {110, "Computer Science", "Sanjay Kumar"},
                        {111, "English Literature", "R.K. Narayan"},
                        {112, "History", "R.S. Sharma"},
                        {113, "Geography", "Majid Hussain"},
                        {114, "Environmental Science", "Erach Bharucha"},
                        {115, "Zoology", "S.S. Lal"}
                    };

                    for (Object[] b : initialBooks) {
                        pstmt.setInt(1, (Integer) b[0]);
                        pstmt.setString(2, (String) b[1]);
                        pstmt.setString(3, (String) b[2]);
                        pstmt.addBatch();
                    }
                    pstmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            System.err.println("\n[Database Warning] Could not connect to MSSQL Server: " + e.getMessage());
            System.err.println("Please ensure MSSQL Server is running and check 'db.properties'.\n");
        }
    }
}
