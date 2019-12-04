package view.boardComponents;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates the panel that will contain the columns
 * The user should be able to add columns.
 * The columns will welcome the Kanban cards.
 */
@ClassAnnotation(
        classAuthors = {"Jeanne", "Petra"},
        creationDate = "09/11/2019",
        lastEdit = "26/11/2019"
)

public class BoardPanel extends JPanel {

    private ArrayList<KanbanColumn> columns;

    public BoardPanel() {
        super();
        initialiseBoard();
    }

    public void initialiseBoard() {
        columns = new ArrayList<>();
        setBackground(Color.black);
        //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new FlowLayout());
    }


    public void addColumn(KanbanColumn column) {
        columns.add(column);
        add(column);
        add(Box.createRigidArea(new Dimension(5, 0)));
        revalidate();
    }

    public void removeColumn(KanbanColumn column) {
        remove(column);
        revalidate();
        repaint();
    }

    public void clear() {
        removeAll();
    }

    public ArrayList<KanbanColumn> getColumns() {
        return columns;
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