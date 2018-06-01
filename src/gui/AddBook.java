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
    private JButton save, btnBookList;
    private JPanel panel;
    private Container container;
    private DBService dbService;

    public AddBook(DBService dbService) throws HeadlessException {
        super("Add book app");
        container = this.getContentPane();
        this.dbService = dbService;
        this.initCompnents();
        this.add(panel);
        this.setMinimumSize(new Dimension(640,480));
        this.setPreferredSize(new Dimension(800,600));
        this.setMaximumSize(new Dimension(1024,768));
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initCompnents() {


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new TitledBorder("Eingabemaske für Bücher:"));


        heading = new JLabel("Add Book");
        labelInputName = new JLabel("Name des Buches:");
        labelInputAuthor = new JLabel("Name des Autors:");
        labelInputIsbn = new JLabel("ISBN-Nummer eintragen:");
        labelStatus = new JLabel("Status: ");


        name = new JTextField(5);

        name.setMaximumSize(new Dimension(400, 20));
        name.setBorder(new TitledBorder("Name des Buches:"));
        author = new JTextField();
        isbn = new JTextField();
        save = new JButton("SAVE");
        btnBookList = new JButton("Zeige Bücher");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inhaltTextName = name.getText();
                String inhaltTextAuthor = author.getText();
                String inhaltTextisbn = isbn.getText();

                if (checkInputInteger(inhaltTextisbn) && checkInputLength(inhaltTextName) && checkInputLength(inhaltTextAuthor)) {

                    try {
                        if (dbService.insertBook(inhaltTextName, inhaltTextAuthor, inhaltTextisbn)) {
                            clearTextFields();
                            setLabelStatus(true);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    setLabelStatus(false);
                }


            }
        });


        btnBookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        panel.add(btnBookList);
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

    private boolean checkInputInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean checkInputLength(String input) {
        if (input.length() > 10) {
            System.out.println("Text zu groß! Maximum 10 Zeichen! => " + input);
            return false;
        }
        return true;
    }


}
