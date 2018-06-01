package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/mywebapp";
    private final String USER = "root2";
    private final String PASSWORT = "mysql";
    private Statement statement;
    private PreparedStatement preparedStatement;


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


    public List<Book> getAllBooks() throws SQLException {
        List<Book> bookList = new ArrayList<>();
        String sql = "select * from book";
        ResultSet resultSet = this.statement.executeQuery(sql);
        while (resultSet.next()) {
            String name = resultSet.getString(2);
            String author = resultSet.getString(3);
            String isbn = resultSet.getString(4);
            Book book = new Book(name,author,isbn);
            bookList.add(book);
        }
        return bookList;
    }


    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
