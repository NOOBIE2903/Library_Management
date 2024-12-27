import java.util.Scanner;
import java.sql.*;

public class Demo {
    private static final String username = "root";
    private static final String password = "abhi74151";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement stmt = conn.createStatement();
                String query = "select * from books";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String BookID = rs.getString("BookID");
                    String category = rs.getString("Category");
                    String name = rs.getString("Name");
                    String author = rs.getString("Author");
                    int copies = rs.getInt("Copies");

                    System.out.println();
                    System.out.println("==============================");
                    System.out.println("BookID: " + BookID);
                    System.out.println("Category: " + category);
                    System.out.println("Name: " + name);
                    System.out.println("Author: " + author);
                    System.out.println("Copies: " + copies);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
