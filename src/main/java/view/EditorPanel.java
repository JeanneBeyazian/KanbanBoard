package view;

import javafx.scene.Group;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;

public class EditorPanel extends JPanel {

    public EditorPanel() {

        setBackground(new java.awt.Color(230,230,250));
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(box);
        this.setBorder(new EmptyBorder(new Insets(30,10,10,30)));

        JLabel title = new JLabel("> EDITOR PANEL");
        add(title);
        add(new JSeparator());

        // Create buttons and labels
        JButton addButton = createButton(" + ");
        JButton removeButton =  createButton(" - ");
        JLabel addLabel = new JLabel("Insert");
        JLabel removeLabel = new JLabel("Delete");

        // New group layout to display buttons and labels
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
                        .addComponent(addButton).addComponent(removeButton))
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
        );

        group.linkSize(SwingConstants.HORIZONTAL, addButton, removeButton);

        add(pane);

        add(new JSeparator());

        // Create an exit button
        add(createExitButton());

    }

    private JButton createButton(String buttonName) {

        JButton button = new JButton(buttonName);
        button.setBackground(new java.awt.Color(200,200,250));
        button.setBorderPainted(false);
        return button;
    }

    private JButton createExitButton() {

        JButton exitButton = new JButton("Exit Application");
        exitButton.setBounds(60, 60, 80, 30);
        exitButton.addActionListener(e->System.exit(0));
        exitButton.setToolTipText("Quit Indigo-Kanban?");
        exitButton.setBackground(new java.awt.Color(250, 105, 128));
        exitButton.setBorderPainted(false);

        return exitButton;

    }

}
