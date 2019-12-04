package view.frames;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import view.boardComponents.KanbanCardButton;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;


/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
public class KanbanCard extends JFrame {

    private static int id;
    private KanbanCardButton cardButton;
    private JTextArea title;
    private JTextArea description;
    private JTextArea storyPoints;


    public KanbanCard(KanbanCardButton button, String name, String description, int storyPoints) {
        ++id;
        this.cardButton = button;
        add(makeContainerPanel(name,description,storyPoints));
        initialise();
    }

    private void initialise() {
        setSize(500, 500);
        setTitle(title.getText());
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
    }

    private JPanel makeContainerPanel(String titleIn, String descriptionIn, int storyPointsIn) {

        // Create Text areas
        title = new JTextArea(titleIn);
        storyPoints = new JTextArea(String.valueOf(storyPointsIn));
        createDescriptionArea(descriptionIn);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLUE);
        //panel.setSize(600,550);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(description);
        panel.add(storyPoints);
        panel.add(createDeleteButton());

        return panel;

    }

    private void createDescriptionArea(String descriptionIn){
        description = new JTextArea(descriptionIn);
        description.setRows(20);
        description.setColumns(30);
    }

    public JButton createDeleteButton() {
        JButton delete = new JButton("delete");
        delete.setBounds(500,500,5,5);
        delete.addActionListener(e-> {
            try {
                cardButton.getColumn().removeCard(cardButton);
            } catch (KanbanObjectNotFoundException k) {
                delete.setEnabled(false); // disable button if not found
                delete.setText(delete.getText() + " (Error: Card not found)");
                System.out.println("Error: Card not found");
                k.printStackTrace();
            }
        });
        return delete;
    }


    public int getId(){
        return id;
    }

}
