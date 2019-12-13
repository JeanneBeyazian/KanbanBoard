package view.frames.editBoardFrames;

import controller.exceptions.KanbanObjectNotFoundException;
import javafx.util.Pair;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.AbstractMap;
import java.util.Map;

import static controller.OptionPanes.*;


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

        Map<JComponent, Pair<Integer,Integer>> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(thisColumnLabel, new Pair<>(0,2)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(thisColumnBox, new Pair<>(1,2)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(columnsLabel, new Pair<>(0,3)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(columnsBox, new Pair<>(1,3))
        );

        placeComponents(map, 4);

    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            if(!columnsBox.isEnabled()) {
                missingComponentError("Column");
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
            commandNotFoundError();
        }

    }
}
