package view;

import annotations.ClassAnnotation;
import controller.ColumnRole;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates the panel that will contain the columns
 * The user should be able to add columns.
 * The columns will welcome the Kanban cards.
 */
@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "09/11/2019",
        lastEdit = "22/11/2019"
)

public class BoardPanel extends JPanel {

    // TODO (J) : figure out how to remove the west border layout empty space

    public BoardPanel() {
        super();
        initialiseBoard();
    }

    public void initialiseBoard() {

        setBackground(Color.black);

        // TESTING PURPOSE : test columns
        KanbanColumn first = new KanbanColumn("First", ColumnRole.BACKLOG);
        KanbanColumn second = new KanbanColumn("Second", ColumnRole.COMPLETED);
        KanbanColumn third = new KanbanColumn("Third", ColumnRole.IN_PROGRESS);

        // Create vertical separation with JSeparator
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //add(Box.createHorizontalGlue());

        addColumn(first);
        addColumn(second);
        addColumn(third);
        removeColumn(second);

        reset();

    }



    public void addColumn(KanbanColumn column) {
        add(column);
        // TODO : add the following in column class directly
        add(Box.createRigidArea(new Dimension(5, 0)));
        revalidate();
    }

    public void removeColumn(KanbanColumn column) {
        remove(column);
        revalidate();
    }

    public void reset() {
        removeAll();
    }
}