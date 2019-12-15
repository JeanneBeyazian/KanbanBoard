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
        lastEdit = "15/12/2019"
)
/**
 * Frame for moving a card from a column to another
 */
public class MoveCardFrame extends EditorFrame {

    // Frame specific components
    private JComboBox<String> columnsBox;
    private JComboBox<String> thisColumnBox;

    private KanbanCardButton currentCard;


    public MoveCardFrame(BoardPanel currentPanel, KanbanCardButton currentCard) {

        super(currentPanel);
        submit.addActionListener(this);
        this.currentCard = currentCard;
        this.setUpFrame();

    }

    /**
     * Set up the frame and its components :
     * add a this column label and combo box, chosen column label and combo box.
     */
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

    /**
     * Action to move a card from a column to another
     * @param event
     */
    public void actionPerformed(ActionEvent event) {

        // Make sure source is known
        if (event.getSource() == submit) {

            if(!columnsBox.isEnabled()) {
                missingComponentError("Column");
                return;
            }

            String columnName = String.valueOf(columnsBox.getSelectedItem());

            KanbanColumn columnToAdd = null;
            KanbanColumn currentColumn = currentCard.getColumn();


            // Get name of selected column and add card to it
            try {
                columnToAdd = currentPanel.getColumnByTitle(columnName);
                columnToAdd.addCard(currentCard);
                Change change = new Change(Change.ChangeType.MOVE, currentCard.getCardButtonTitle(), currentCard, currentColumn, columnToAdd);
                ChangeLog.getInstance().addChange(change);
            } catch (UnknownKanbanObjectException u){
                System.out.println("Failed to log.");
                u.printStackTrace();
            } catch (KanbanObjectNotFoundException e) {
                System.out.println("Error: Column not found");
                e.printStackTrace();
                return;
            }

            // Remove card from old parent column
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
