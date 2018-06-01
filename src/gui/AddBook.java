package gui;

import model.DBService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddBook extends JFrame {

    private JLabel heading, labelInputName, labelInputAuthor, labelInputIsbn, labelStatus;
    private JTextField name, author, isbn;
    private JButton save;
    private JPanel panel;
    private Container container;
    private DBService dbService;

    public AddBook(DBService dbService) throws HeadlessException {
        super("Add book app");
        container = this.getContentPane();
        this.dbService = dbService;
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
        labelInputName = new JLabel("Name des Buches:");
        labelInputAuthor = new JLabel("Name des Autors:");
        labelInputIsbn = new JLabel("ISBN-Nummer eintragen:");
        labelStatus = new JLabel("Status: ");



        name = new JTextField();
        name.setMaximumSize(new Dimension(200,20));
        name.setBorder(new TitledBorder("Name des Buches:"));
        author = new JTextField();
        isbn = new JTextField();
        save = new JButton("SAVE");


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inhaltTextName = name.getText();
                String inhaltTextAuthor = author.getText();
                String inhaltTextisbn = isbn.getText();

                try {
                    if (dbService.insertBook(inhaltTextName, inhaltTextAuthor, inhaltTextisbn)) {
                        clearTextFields();
                        setLabelStatus(true);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        panel.add(heading);
        panel.add(labelInputName);
        panel.add(name);
        panel.add(labelInputAuthor);
        panel.add(author);
        panel.add(labelInputIsbn);
        panel.add(isbn);
        panel.add(save);
        panel.add(labelStatus);


    }

    private void setLabelStatus(boolean b) {
        if (b) {
            this.labelStatus.setText("<html>Status: <font color='green'>Gespeichert!</font></html>");
            //this.labelStatus.setForeground(Color.green);
        } else {
            this.labelStatus.setText("Fehler");
            this.labelStatus.setForeground(Color.red);
        }
    }

    private void clearTextFields() {
        this.name.setText("");
        this.author.setText("");
        this.isbn.setText("");

    }


}
