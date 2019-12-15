package model;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import controller.exceptions.ChangeTypeNotImplementedException;
import controller.exceptions.KanbanObjectNotFoundException;
import view.KanbanBoard;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;
import view.frames.KanbanCard;

import java.util.List;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "14/12/2019",
        lastEdit = "14/12/2019"
)
/**
 * Translates log entries into real commands applied to a newly created board.
 * Allows the user to see a specific version of a board.
 */
public class LogTranslater {

    private List<Change> log;
    private KanbanBoard board = new KanbanBoard();

    public LogTranslater(List<Change> log) {

        this.log = log;
        translate();
        board.getEditorPanel().setVisible(false);
        board.setVisible(true);

    }


    /**
     * Translation process : for each changes in the given log, checks type of change and reproduces it
     * in a new empty KanbanBoard.
     */
    private void translate() {

        for (Change change : log) {

            // Get the changetype for this change
            Change.ChangeType changeType = change.getChangeType();

            if (changeType==Change.ChangeType.ADD) add(change);
            if (changeType == Change.ChangeType.REMOVE) remove(change);
            if (changeType == Change.ChangeType.UPDATE) update(change);
            if (changeType == Change.ChangeType.MOVE) move(change);
            if (changeType == Change.ChangeType.CLEAR) clear(change);
        }
    }


    /**
     * If an object has been added to the board
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void add(Change change) {

        Class<?> classType = change.getObject().getClass();

        if (classType == KanbanBoard.class) {
            board.setBoardName("[LOG VERSION UNTIL CHANGE "
                    + log.get(log.size()-1).getId() +" OF BOARD :  "
                    + change.getobjTitle()
                    + "] - Please do not modify past versions.");
        }
        else if (classType == KanbanColumn.class){
            board.getBoard().addColumn((KanbanColumn) change.getObject());
        }
        else if (classType== KanbanCard.class) {
            try {
                board.getBoard().getColumnByTitle(change.getNewParentTitle())
                        .addCard((KanbanCardButton)change.getObject());
            } catch (Exception e){
                new KanbanObjectNotFoundException();
            }
        }

    }


    /**
     * If an object has been removed from the board
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void remove(Change change) {

        Class<?> classType = change.getObject().getClass();


        if (classType== KanbanColumn.class) {
            board.remove((KanbanColumn)change.getObject());
        }
        else if (classType== KanbanCard.class || change.getObject() == KanbanCardButton.class) {
            try {
                KanbanColumn old = (KanbanColumn) change.getOldParent();
                board.getBoard().getColumnByTitle(old.getColumnTitle())
                        .removeCard((KanbanCardButton)change.getObject());
            } catch (Exception e){
                new KanbanObjectNotFoundException();
            }
        }

    }


    /**
     * If an object from the board has been updated
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void update(Change change)  {

        Class<?> classType = change.getObject().getClass();

        // If a column has been updated
        if (classType== KanbanColumn.class) {
            if (change.getUpdatedField().equals("role")){
                ((KanbanColumn)change.getObject()).setRole((ColumnRole)change.getUpdatedValue());
            }
            else if (change.getUpdatedField().equals("title")) {
                ((KanbanColumn)change.getObject()).setColumnTitle((String)change.getUpdatedValue());
            }
        }

        // If a card has been updated
        else if (classType== KanbanCard.class || change.getObject() == KanbanCardButton.class) {

            // Get the card object
            KanbanCard card = (KanbanCard)change.getObject();

            if (change.getUpdatedField().equals("title")){
                card.getCardTitleField().setText((String)change.getUpdatedValue());
            }
            else if (change.getUpdatedField().equals("description")) {
                card.getDescription().setText((String)change.getUpdatedValue());
            }
            else if (change.getUpdatedField().equals("story points")) {
                card.getStoryPointsBox().setSelectedItem((Integer.valueOf((String)change.getUpdatedValue())));
            }

            // Update the card information
            card.update();
        }

    }

    /**
     * If an object has been moved on the board
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void move(Change change) {

        Class<?> classType = change.getObject().getClass();
        KanbanColumn prevCol = (KanbanColumn) change.getOldParent();

        try {
            board.getBoard().getColumnByTitle(change.getNewParentTitle())
                    .addCard((KanbanCardButton)change.getObject());
            board.getBoard().getColumnByTitle(prevCol.getColumnTitle())
                    .removeCard((KanbanCardButton) change.getObject());

        } catch (Exception e){
            new KanbanObjectNotFoundException();
        }

    }

    /**
     * If an object of the board has been cleared
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void clear(Change change)  {

        // Check if column or board has been cleared
        if (change.getObject() == KanbanColumn.class) {
            KanbanColumn col = (KanbanColumn)change.getObject();
            col.clearColumn();
        }
        else if (change.getObject() == KanbanBoard.class) {
            ((KanbanBoard)change.getObject()).getBoard().clearBoard();
        }

    }

}







