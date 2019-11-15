package view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;

public class EditorPanel extends JPanel {

    public EditorPanel() {
        initialiseEditorPanel();
    }

    private void initialiseEditorPanel() {


        setBackground(new java.awt.Color(5, 53, 67));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setSize(800, 700);

        this.setBorder(new EmptyBorder(new Insets(10,30,10,30)));

        JLabel title = createLabel("EDITOR PANEL");
        add(title);
        add(new JSeparator());

        // New group layout to display buttons and labels
        add(createUserInterface());
        add(new JSeparator());

        // Create an exit button
        add(createExitButton());
    }

    private JPanel createUserInterface() {

        // Create buttons and labels
        JButton addButton = createButton(" + ");
        JButton removeButton =  createButton(" - ");
        JButton editButton = createButton("Edit");
        JLabel addLabel = createLabel("Insert");
        JLabel removeLabel = createLabel("Delete");


        JPanel pane = new JPanel();
        pane.setOpaque(false);
        GroupLayout group = new GroupLayout(pane);
        pane.setLayout(group);
        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);

        // First, horizontal grouping
        group.setHorizontalGroup(
                group.createSequentialGroup()
                        .addGroup(group.createParallelGroup(LEADING)
                                .addComponent(addButton).addComponent(removeButton)).addComponent(editButton)
                        .addGroup(group.createParallelGroup()
                                .addComponent(addLabel).addComponent(removeLabel))
        );


        // Second, vertical grouping
        group.setVerticalGroup(
                group.createSequentialGroup()
                        .addGroup(group.createParallelGroup(BASELINE)
                                .addComponent(addButton).addComponent(addLabel))
                        .addGroup(group.createParallelGroup(BASELINE)
                                .addComponent(removeButton).addComponent(removeLabel))
                        .addGap(20)
                        .addGroup(group.createParallelGroup(BASELINE)
                                .addComponent(editButton))
        );

        group.linkSize(SwingConstants.HORIZONTAL, addButton, removeButton);


        return pane;
    }

    private JLabel createLabel(String labelName) {
        JLabel label = new JLabel(labelName);
        label.setForeground(Color.lightGray);
        return label;
    }

    private JButton createButton(String buttonName) {

        JButton button = new JButton(buttonName);
        // Modify font : button.setFont(Font.getFont("arial"));
        button.setBackground(new java.awt.Color(21, 34, 59));
        button.setForeground(Color.lightGray);
        button.setBorderPainted(false);
        return button;
    }

    private JButton createExitButton() {

        JButton exitButton = new JButton("Exit Application");
        exitButton.addActionListener(e->System.exit(0));
        exitButton.setToolTipText("Quit Indigo-Kanban?");
        exitButton.setBackground(new java.awt.Color(250, 105, 128));
        exitButton.setBorderPainted(false);

        return exitButton;

    }

}
