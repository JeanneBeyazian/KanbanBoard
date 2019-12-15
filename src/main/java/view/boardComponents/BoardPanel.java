package view.boardComponents;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import model.Change;
import model.ChangeLog;
import view.frames.KanbanBoard;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static controller.OptionPanes.missingComponentError;


@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Petra",
        creationDate = "09/11/2019",
        lastEdit = "15/12/2019"
)
/**
 * This class creates the panel that will contain the columns
 * The user should be able to add columns.
 * The columns will welcome the Kanban cards.
 */
public class BoardPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	// List of all columns in the board
    private ArrayList<KanbanColumn> columns;

    // Current board work in progress counter and set limit
    private int WIPlimit;
    private int WIPcount;

    private KanbanBoard parentBoard;


    /** Constructor for BoardPanel */
    public BoardPanel(KanbanBoard parentBoard) {
        super();
        columns = new ArrayList<>();
        this.parentBoard = parentBoard;
        initialiseBoard();
    }

    /**
     * Set up the board
     */
    public void initialiseBoard() {
        setBackground(Color.black);
        setLayout(new FlowLayout());
        WIPcount = 0;
        WIPlimit = 0;
    }

    /**
     * Add a column to this board
     * @param column KanbanColumn
     */
    public void addColumn(KanbanColumn column) {
        columns.add(column);
        add(column);
        add(Box.createRigidArea(new Dimension(5, 0)));
        revalidate();
    }

    /**
     * Remove an input column from the board
     * @param column KanbanColumn
     */
    public void removeColumn(KanbanColumn column) {
    	columns.remove(column);
        remove(column);
        revalidate();
        repaint();
    }

    /**
     * Remove all columns from the board
     */
    public void clearBoard() {

        // Make sure board isn't already empty
        if (isEmpty()) {
            missingComponentError("Column");
            return;
        }
        try {
            removeAll();
            revalidate();
            repaint();
            columns.clear();
            Change change = new Change(Change.ChangeType.CLEAR, parentBoard.getBoardName(), this);
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }

    }

    /**
     * @return true if the board is empty of columns
     */
    public boolean isEmpty() {
        return (columns.isEmpty());
    }

    /**
     * Get the list of all columns in the board
     * @return
     */
    public ArrayList<KanbanColumn> getColumns() {
        return columns;
    }

    /**
     * Increment the current WIP counter by input story points
     * @param points from added card
     */
    public void incrementWIPCount(int points){
        WIPcount += points;
    }

    /**
     * Decrement the current WIP vounter by input story points
     * @param points from removed card
     */
    public void decrementWIPCount(int points){
        WIPcount -= points;
    }

    /**
     * Set or reset current WIP limitation
     * @param WIPlimit
     */
    public void setWIPlimit(int WIPlimit) {
        this.WIPlimit = WIPlimit;
    }

    /**
     * Get the current value of the WIP counter
     * @return WIPcount
     */
    public int getWIPcount() {
        return WIPcount;
    }

    /**
     * Get the current set value of the WIP limit
     * @return WIPLimit
     */
    public int getWIPlimit() {
        return WIPlimit;
    }

    /**
     *  Get column having a given title
     * @param title title of the column we're searching for
     * @return reference to column
     * @throws KanbanObjectNotFoundException
     */
    public KanbanColumn getColumnByTitle(String title) throws KanbanObjectNotFoundException {
        for (KanbanColumn col : columns) {
            if (col.getColumnTitle().equals(title)) {
                return col;
            }
        }
        throw new KanbanObjectNotFoundException(KanbanColumn.class);
    }
}