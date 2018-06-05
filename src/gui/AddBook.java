package gui;

import model.DBService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;

public class AddBook extends JFrame {

    private JLabel heading, labelInputName, labelInputAuthor, labelInputIsbn, labelStatus;
    private JTextField name, author, isbn;
    private JButton save, exit, openFile, createFile;
    private JPanel panel;
    private Container container;
    private DBService dbService;

    public AddBook(DBService dbService) throws HeadlessException {
        super("Add book app");
        container = this.getContentPane();
        this.dbService = dbService;
        this.initCompnents();
        this.add(panel);
        this.setMinimumSize(new Dimension(640, 480));
        this.setPreferredSize(new Dimension(800, 600));
        this.setMaximumSize(new Dimension(1024, 768));
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("Window aktiv!");
            }
        });

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
        exit = new JButton("EXIT");
        openFile = new JButton("Open file");
        createFile = new JButton("Create file");

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToDatabase();
            }
        });


        save.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    saveToDatabase();
                }
            }
        });


        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                save.setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                save.setBackground(null);
            }
        });


        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                //jFileChooser.setCurrentDirectory(new File("C:\\"));
                jFileChooser.showOpenDialog(AddBook.this);
                //jFileChooser.showSaveDialog(AddBook.this);

                try {
                    loadFile(jFileChooser.getSelectedFile());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });


        createFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showSaveDialog(AddBook.this);
                File file = jFileChooser.getSelectedFile();
                createFile(file);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();

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
        panel.add(openFile);
        panel.add(createFile);
        panel.add(exit);
        panel.add(labelStatus);

    }



    private void exitApp() {
        int n = JOptionPane.showConfirmDialog(null, "Anwendung beenden", "titel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_OPTION) {
            System.exit(1);
        }
    }

    private void saveToDatabase() {
        String inhaltTextName = name.getText();
        String inhaltTextAuthor = author.getText();
        String inhaltTextisbn = isbn.getText();

        if (checkInputInteger(inhaltTextisbn) && checkInputLength(inhaltTextName) && checkInputLength(inhaltTextAuthor)) {
            if (dbService.insertBook(inhaltTextName, inhaltTextAuthor, inhaltTextisbn)) {
                clearTextFields();
                setLabelStatus(true);

            } else {
                setLabelStatus(false);
            }
        }
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

    private void loadFile(File file) throws IOException {
        InputStream fi = new FileInputStream(file);
        byte[] bytes = new byte[((int) file.length())];
        fi.read(bytes);
        for (int i = 0; i < bytes.length; i++) {
            byte aByte = bytes[i];
            System.out.print(((char) aByte));
        }
        fi.close();

        /*Reader fr = new FileReader(file);
        char[] chars = new char[((int) file.length())];
        for (int i = 0; fr.read() != -1; i++) {
            chars[i] = (char) fr.read();
        }
        String ausgabe = String.copyValueOf(chars);
        System.out.println(ausgabe);*/
    }

    private void createFile(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(name.getText());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
