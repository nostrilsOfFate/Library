package lena.library.dao;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.*;

public class main {

    public static void main(String[] args) throws SQLException, ParserConfigurationException, ClassNotFoundException {
        String userName = "root";
        String password = "root";
        String connectionURL = "jdbc:mysql://localhost:3306?useSSL=false";
        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Creating database connection...");
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = connection.createStatement()) {
            System.out.println("We are connected!");
            System.out.println("Executing statement...");
            System.out.println("----------------------");

            String sql;
                sql = "SELECT * FROM library.authors";

                ResultSet resultSet = statement.executeQuery(sql);
                System.out.println("Retrieving data from database...");
                System.out.println("\nauthors:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("\n================\n");
                    System.out.println("id: " + id);
                    System.out.println("Name: " + name);
                    //
            }
            System.out.println("Closing connection and releasing resources...");
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Finish!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

