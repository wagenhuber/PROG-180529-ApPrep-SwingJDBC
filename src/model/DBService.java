package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {

    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/mywebapp";
    private final String USER = "root2";
    private final String PASSWORT = "mysql";
    private Statement statement;

    public DBService() throws SQLException {

        connection = DriverManager.getConnection(URL, USER, PASSWORT);
        statement = connection.createStatement();

    }


    public boolean insertBook(String inhaltTextName, String inhaltTextAuthor, String inhaltTextisbn) throws SQLException {
        String sql = "insert into book (name,author,isbn) values ('" + inhaltTextName + "','" + inhaltTextAuthor + "','" + inhaltTextisbn + "')";
        try {
            this.statement.execute(sql);
        } catch (SQLException e) {
            return false;
        }
        //statement.close();
        return true;
    }

    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
