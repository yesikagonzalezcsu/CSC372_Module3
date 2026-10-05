package csc372module3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserInterface extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea textBox;
    private JMenuItem dateTimeItem;
    private JMenuItem saveItem;
    private JMenuItem colorItem;
    private JMenuItem exitItem;

    private Color randomGreen;

    public UserInterface() {

        // Create the frame
        setTitle("User Interface Application");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the text box
        textBox = new JTextArea();
        textBox.setEditable(false);
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);

        // Add text box to the frame
        add(new JScrollPane(textBox), BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the menu
        JMenu menu = new JMenu("Menu");

        // Create the four menu items
        dateTimeItem = new JMenuItem("Show Date and Time");
        saveItem = new JMenuItem("Save to File");
        colorItem = new JMenuItem("Change Green Background");
        exitItem = new JMenuItem("Exit");

        // Add action listeners
        dateTimeItem.addActionListener(this);
        saveItem.addActionListener(this);
        colorItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add menu items to the menu
        menu.add(dateTimeItem);
        menu.add(saveItem);
        menu.add(colorItem);
        menu.addSeparator();
        menu.add(exitItem);

        // Add menu to menu bar
        menuBar.add(menu);

        // Add menu bar to frame
        setJMenuBar(menuBar);

     // Generate one random hue within the green range
        Random random = new Random();
        float hue = 0.25f + random.nextFloat() * (0.4167f - 0.25f);

        randomGreen = Color.getHSBColor(hue, 1.0f, 1.0f);
        
        System.out.println("Hue: " + hue);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Menu option 1: Display date and time
        if (e.getSource() == dateTimeItem) {

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

            textBox.setText(now.format(formatter));
        }

        // Menu option 2: Save text to log.txt
        else if (e.getSource() == saveItem) {

            try {
                FileWriter writer = new FileWriter("log.txt");

                writer.write(textBox.getText());

                writer.close();

                JOptionPane.showMessageDialog(this,
                        "Text saved to log.txt.");

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error saving the file.");
            }
        }

        // Menu option 3: Change background to the generated green
        else if (e.getSource() == colorItem) {

        	textBox.setBackground(randomGreen);

        }

        // Menu option 4: Exit
        else if (e.getSource() == exitItem) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new UserInterface();
    }
}