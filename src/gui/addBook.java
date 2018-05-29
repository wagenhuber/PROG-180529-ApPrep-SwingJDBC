package gui;

import javax.swing.*;
import java.awt.*;

public class addBook extends JFrame {

    private JLabel heading;
    private JTextField name, author, isbn;
    private JButton save;
    private JPanel panel;
    private Container container;

    public addBook() throws HeadlessException {
        super("Add book app");
        container = this.getContentPane();
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

        panel.add(heading);
        panel.add(name);
        panel.add(author);
        panel.add(isbn);
        panel.add(save);

    }

    public static void main(String[] args) {
        new addBook();
    }
}
