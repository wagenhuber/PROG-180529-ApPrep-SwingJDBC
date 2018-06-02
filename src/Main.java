import gui.AddBook;
import gui.BookList;
import model.DBService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DBService dbService = new DBService();
        AddBook addBook = new AddBook(dbService);
        //new BookList(dbService);
    }
}
