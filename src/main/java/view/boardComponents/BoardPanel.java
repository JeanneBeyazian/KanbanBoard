package view.boardComponents;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import model.Change;
import model.ChangeLog;
import view.KanbanBoard;
import view.frames.KanbanCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static controller.OptionPanes.missingComponentError;


@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Petra",
        creationDate = "09/11/2019",
        lastEdit = "14/12/2019"
)
/**
 * This class creates the panel that will contain the columns
 * The user should be able to add columns.
 * The columns will welcome the Kanban cards.
 */
public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private ArrayList<KanbanColumn> columns;

    private int WIPlimit;
    private int WIPcount;

    private KanbanBoard parentBoard;

    public BoardPanel(KanbanBoard parentBoard) {
        super();
        initialiseBoard();
        WIPcount = 0;
        WIPlimit = 0;
        this.parentBoard = parentBoard;
    }

    public void initialiseBoard() {
        columns = new ArrayList<>();
        setBackground(Color.black);
        setLayout(new FlowLayout());
    }
    

    public void addColumn(KanbanColumn column) {
        columns.add(column);
        add(column);
        add(Box.createRigidArea(new Dimension(5, 0)));
        revalidate();
    }

    public void removeColumn(KanbanColumn column) {
    	columns.remove(column);
        remove(column);
        revalidate();
        repaint();
    }

    public void clearBoard() {
        if (isEmpty()) {
            missingComponentError("Column");
            return;
        }
        try {
            columns.clear();
            removeAll();
            revalidate();
            repaint();
            Change change = new Change(Change.ChangeType.REMOVE, parentBoard.getBoardName(), this);
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }


    }

    public boolean isEmpty() {
        return (columns.isEmpty());
    }

    public ArrayList<KanbanColumn> getColumns() {
        return columns;
    }

    public void incrementWIPCount(int points){
        WIPcount += points;
    }

    public void decrementWIPCount(int points){
        WIPcount -= points;
    }

    public void setWIPlimit(int WIPlimit) {
        this.WIPlimit = WIPlimit;
    }

    public int getWIPcount() {
        return WIPcount;
    }

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