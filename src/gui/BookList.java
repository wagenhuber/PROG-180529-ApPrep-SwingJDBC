package gui;

import model.Book;
import model.DBService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class BookList extends JFrame {

    private JPanel panel;
    private JTable table;
    private Container container;
    private DBService dbService;

    public BookList(DBService dbService) throws HeadlessException, SQLException {
        this.dbService = dbService;
        container = getContentPane();
        this.setPreferredSize(new Dimension(640, 480));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        this.setVisible(true);

        getAllBooks();
    }

    private void initComponents() {
        panel = new JPanel();
        panel.setBorder(new TitledBorder("Alle Bücher in der Liste"));
        table = new JTable();

        panel.add(table);


    }


    public void getAllBooks() throws SQLException {
        List<Book> bookList = dbService.getAllBooks();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
    }

}
