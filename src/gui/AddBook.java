package gui;

import model.DBService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBook extends JFrame {

    private JLabel heading;
    private JTextField name, author, isbn;
    private JButton save;
    private JPanel panel;
    private Container container;
    private DBService dbService;

    public AddBook(DBService dbService) throws HeadlessException {
        super("Add book app");
        container = this.getContentPane();
        this.dbService = dbService;
        //container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.initCompnents();
        this.add(panel);
        this.setResizable(false);
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initCompnents() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        heading = new JLabel("Add Book");

        name = new JTextField();
        author = new JTextField();
        isbn = new JTextField();
        save = new JButton("SAVE");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inhaltTextName = name.getText();
                String inhaltTextAuthor = author.getText();
                String inhaltTextisbn = isbn.getText();
                dbService.insertBook(inhaltTextName, inhaltTextAuthor, inhaltTextisbn);
            }
        });

        panel.add(heading);
        panel.add(name);
        panel.add(author);
        panel.add(isbn);
        panel.add(save);

    }


}
