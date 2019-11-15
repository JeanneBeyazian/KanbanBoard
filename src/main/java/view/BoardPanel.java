package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates the panel that will contain the columns
 * The user should be able to add columns.
 * The columns will welcome the Kanban cards.
 */

public class BoardPanel extends JPanel {

    ArrayList<KanbanColumn> columns;

    public BoardPanel() {
        super();
        columns = new ArrayList<>();
        initialiseBoard();
    }

    public void initialiseBoard() {

        //KanbanColumn first = new KanbanColumn("First");
        //add(first, TOP_ALIGNMENT);
        //first.setVisible(true);

        // Create vertical separation with JSeparator



    }


    public void addColumn() {

    }

    public void reset(){
        //columns.clear();
    }
}