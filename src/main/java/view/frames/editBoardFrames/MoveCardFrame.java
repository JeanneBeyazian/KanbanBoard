package view.frames.editBoardFrames;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import javafx.util.Pair;
import model.Change;
import model.ChangeLog;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.AbstractMap;
import java.util.Map;

import static controller.OptionPanes.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Petra",
        creationDate = "28/11/2019",
        lastEdit = "12/12/2019"
)
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
                columnToAdd.addCard(currentCard);
                Change change = new Change(Change.ChangeType.MOVE, currentCard.getCardButtonTitle(), currentCard, currentColumn, columnToAdd);
                ChangeLog.getInstance().addChange(change);
            } catch (UnknownKanbanObjectException u){
                System.out.println("Failed to log.");
                u.printStackTrace();
            }

            catch (KanbanObjectNotFoundException e){
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

            dispose();
        }
        else {
            commandNotFoundError();
        }

    }
}
