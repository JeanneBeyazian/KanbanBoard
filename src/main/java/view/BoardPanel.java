package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates the panel that will contain the columns
 * The user should be able to add columns.
 * The columns will welcome the Kanban cards.
 */

public class BoardPanel extends JPanel {

    ArrayList<KanbanColumn> columns;

    public BoardPanel() {
        super();
        columns = new ArrayList<>();
        initialiseBoard();
    }

    public void initialiseBoard() {

        KanbanColumn first = new KanbanColumn("First");
        add(first, BorderLayout.NORTH);
        first.setVisible(true);

        // Create vertical separation with JSeparator

        // Create buttons: add, exit
        createButtons();


    }

    public void createButtons(){

        // For now only contains an exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(60, 60, 80, 30);
        exitButton.addActionListener(e->System.exit(0));
        exitButton.setToolTipText("Quit Indigo-Kanban?");
        add(exitButton, BorderLayout.SOUTH);

        // Add button (not operational)
        JButton addButton = new JButton("+");
        //addButton.setBackground(Color.GRAY);
        addButton.setBounds(30, 30, 30, 30);
        addButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add(addButton, BorderLayout.EAST);

    }

    public void addColumn() {

    }

    public void reset(){
        //columns.clear();
    }
}