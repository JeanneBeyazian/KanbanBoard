package view.frames;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "08/12/2019"
)
/**
 * Help Frame displays information on how to use the application.
 * Accessible in the Kanban Menu Bar.
 */
public class HelpFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 200;

    /**
     * Constructor - help frame set up.
     */
    public HelpFrame() {
        // Set up the JFrame
        this.setTitle("Indigo Kanban Board - Help");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addHelpText();
        addButton();
    }

    /**
     * Help text with instructions on how to use the Kanban board app in a JTextArea.
     * (Non editable)
     */
    private void addHelpText() {

        JTextArea helpTextArea = new JTextArea();
        String helpString = "Welcome to the Indigo Kanban board app!\n\n" +
                "Use the editor panel on the right to add columns and cards to your board. " +
                "You can also remove any component you'd like,\nor clear the whole board.\n" +
                "If you wish to modify some information you entered earlier, " +
                "you can use the buttons at the bottom of each column to either\n" +
                "edit its title and role, or clear all of its cards. Once you add a card to a column," +
                "clicking on it will allow you to view its description,\nand edit it. " +
                "To do so, change any information directly on the card, then click 'save changes'.\n\n" +
                "To exit the application, use the 'Exit application' button at the bottom of the editor panel.";

        helpTextArea.setText(helpString);
        helpTextArea.setMargin(new Insets(10,10,10,10));
        helpTextArea.setEditable(false);
        helpTextArea.setBackground(new java.awt.Color(204, 174, 240));
        add(helpTextArea);
    }

    /**
     * 'Got it' button, returns to board.
     */
    private void addButton() {
        JButton button = new JButton("Got it");
        button.addActionListener(e->this.dispose());
        button.setBackground(new java.awt.Color(133, 113, 240));
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(button, BorderLayout.SOUTH);
    }
}
