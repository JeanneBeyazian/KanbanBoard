package view.frames.editBoardFrames;

import controller.exceptions.KanbanObjectNotFoundException;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MoveCardFrame extends EditorFrame {

    private JComboBox<String> columnsBox;
    private JComboBox<String> thisColumnBox;
    private KanbanCardButton currentCard;

    public MoveCardFrame(BoardPanel currentPanel, KanbanCardButton currentCard) {
        super(currentPanel);
        submit.addActionListener(this);
        this.currentCard = currentCard;
        this.setUpFrame();

    }


    private void setUpFrame() {

        // Set up columns combo boxes
        thisColumnBox = createColumnsList();
        thisColumnBox.setSelectedItem(currentCard.getColumn().getColumnTitle());
        thisColumnBox.setEditable(false);
        JLabel thisColumnLabel = new JLabel("From column:");

        // Set up columns combo boxes
        columnsBox = createColumnsList();
        columnsBox.removeItem(String.valueOf(thisColumnBox.getSelectedItem()));
        JLabel columnsLabel = new JLabel("To column:");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(thisColumnLabel, constraints);
        constraints.gridx = 1;
        container.add(thisColumnBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(columnsLabel, constraints);
        constraints.gridx = 1;
        container.add(columnsBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        container.add(submit, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        container.add(cancel,constraints);

        pack();

    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            if(!columnsBox.isEnabled()) {
                noColumnSelectedError();
                return;
            }

            String columnName = String.valueOf(columnsBox.getSelectedItem());

            KanbanColumn columnToAdd = null;
            KanbanColumn currentColumn = currentCard.getColumn();


            try {
                columnToAdd = currentPanel.getColumnByTitle(columnName);
            }

            catch (KanbanObjectNotFoundException e){
                // TODO = ALERT USER OF COLUMNN ERROR
                System.out.println("Error: Column not found");
                e.printStackTrace();
                return;
            }

            try {
                currentColumn.removeCard(currentCard);
            }
            catch (KanbanObjectNotFoundException e) {
                System.out.println("Error: Column not found");
                e.printStackTrace();
                return;
            }

            columnToAdd.addCard(currentCard);
            dispose();
        }
        else {
            showError("Command not found");
        }

    }
}
