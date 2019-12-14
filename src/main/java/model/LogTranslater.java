package model;

import annotations.ClassAnnotation;
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
    private KanbanBoard board;

    public LogTranslater(List<Change> log) {

        this.log = log;
        board = new KanbanBoard();
        board.setVisible(true);
        try{
            translate();
        }
        catch (Exception e){
        }

    }

    private void translate() throws ChangeTypeNotImplementedException{

        for (Change change : log) {

            Change.ChangeType changeType = change.getChangeType();

                if (changeType == Change.ChangeType.ADD) add(change);
                if (changeType == Change.ChangeType.REMOVE) remove(change);
                if (changeType == Change.ChangeType.UPDATE) update(change);
                if (changeType == Change.ChangeType.MOVE) move(change);
                if (changeType == Change.ChangeType.CLEAR) clear(change);
                throw new ChangeTypeNotImplementedException(changeType);
        }
    }


    /**
     * If an object has been added to the board
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void add(Change change) throws ChangeTypeNotImplementedException {

        if (change.getObject() == KanbanBoard.class) {
            board.setBoardName(change.getobjTitle());
        }
        else if (change.getObject() == KanbanColumn.class){
            board.getBoard().addColumn((KanbanColumn) change.getObject());
        }
        else if (change.getObject() == KanbanCard.class) {
            try {
                board.getBoard().getColumnByTitle(change.getNewParentTitle())
                        .addCard((KanbanCardButton)change.getObject());
            } catch (Exception e){
                new KanbanObjectNotFoundException();
            }
        }
        else throw new ChangeTypeNotImplementedException(change.getChangeType(), change.getObject());

    }

    /**
     * If an object has been removed from the board
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void remove(Change change) throws ChangeTypeNotImplementedException {

        if (change.getObject() == KanbanColumn.class) {
            board.remove((KanbanColumn)change.getObject());
        }
        else if (change.getObject() == KanbanCard.class || change.getObject() == KanbanCardButton.class) {
            try {
                KanbanColumn old = (KanbanColumn) change.getOldParent();
                board.getBoard().getColumnByTitle(old.getColumnTitle())
                        .removeCard((KanbanCardButton)change.getObject());
            } catch (Exception e){
                new KanbanObjectNotFoundException();
            }
        }
        else throw new ChangeTypeNotImplementedException(change.getChangeType(), change.getObject());

    }

    /**
     * If an object from the board has been updated
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void update(Change change) throws ChangeTypeNotImplementedException {
        if (change.getObject() == KanbanColumn.class) {}
        else if (change.getObject() == KanbanCard.class || change.getObject() == KanbanCardButton.class) {}
        else throw new ChangeTypeNotImplementedException(change.getChangeType(), change.getObject());

    }

    /**
     * If an object has been moved on the board
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void move(Change change) throws ChangeTypeNotImplementedException {
        if (change.getObject() == KanbanCard.class || change.getObject() == KanbanCardButton.class) {

        }
        else throw new ChangeTypeNotImplementedException(change.getChangeType(), change.getObject());

    }

    /**
     * If an object of the board has been cleared
     * @param change
     * @throws ChangeTypeNotImplementedException
     */
    private void clear(Change change) throws ChangeTypeNotImplementedException {
        if (change.getObject() == KanbanColumn.class) {
            KanbanColumn col = (KanbanColumn)change.getObject();
            col.clearColumn();
        }
        else if (change.getObject() == KanbanBoard.class) {
            ((KanbanBoard)change.getObject()).getBoard().clearBoard();
        }
        else throw new ChangeTypeNotImplementedException(change.getChangeType(), change.getObject());

    }

}







