package model;

import com.sun.jndi.toolkit.url.UrlUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {

    private Connection connection;
    private final String URL = "jdbc:mysql://localhost/mywebapp";
    private final String USER = "root";
    private final String PASSWORT = "mysql";
    private Statement statement;

    public DBService() throws SQLException {

        connection = DriverManager.getConnection(URL, USER, PASSWORT);

    }


    public void insertBook(String inhaltTextName, String inhaltTextAuthor, String inhaltTextisbn) throws SQLException {
        String sql = "insert into mywebap values ('" + inhaltTextName + "','" + inhaltTextName + "','" + inhaltTextisbn + "')";
        this.statement.execute(sql);
        statement.close();
    }
}
