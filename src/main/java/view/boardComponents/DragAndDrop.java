package view.boardComponents;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The purpose of this class was to open up a new frame representing the current board with columns converted to simple
 * panels containing TextFields for cards. JTextFields are draggable and can be dropped, so the user can choose
 * where to move cards inside the different columns.
 *
 * Once the user is done, he clicks the submit button that modifies the current board following the new set up
 * (convert back to cards and columns).
 *
 * This class was not finished, due to time constraints.
 */
@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "16/12/2019",
        lastEdit = "16/12/2019"
)
public class DragAndDrop extends JFrame {

    // Frame components
    private BoardPanel currentBoard;
    private JPanel buttonPanel;
    private JPanel mainContainer;


    public DragAndDrop(BoardPanel currentBoard) {

        this.currentBoard = currentBoard;
        setTitle("Drag and Drop User Interface");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(mainContainer);
        createButtonPanel();
        createDragAndDropUI();
    }

    private void createButtonPanel() {

        mainContainer = new JPanel();

        buttonPanel = new JPanel();

        JButton submit = new JButton("Submit");
        submit.addActionListener(e->submitBoard());

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e->this.dispose());

        buttonPanel.setPreferredSize(new Dimension(500,50));
        buttonPanel.add(submit, BorderLayout.WEST);
        buttonPanel.add(cancel, BorderLayout.EAST);

        mainContainer.add(buttonPanel,BorderLayout.SOUTH);

    }

    /**
     * Sets up the drag and drop interface : cards become simple JTextFields, columns JPanels.
     */
    private void createDragAndDropUI() {

        ArrayList<KanbanColumn> columnsList = currentBoard.getColumns();
        int numberOfColumns = columnsList.size();

        // Get the longest column on the board
        int maxColSize = 0;
        for (KanbanColumn column : columnsList) {
            int colSize = column.getCards().size();
            if (maxColSize < colSize) maxColSize = colSize;
        }

        // For each column on board
        for (int i=0; i<numberOfColumns; i++) {

            JPanel column = new JPanel();
            column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
            // Adds the column name
            JLabel colName = new JLabel(columnsList.get(i).getColumnTitle());
            column.add(colName);

            // For each card in the longest column
            for (int j=0; j<maxColSize; j++){
                JTextField card = new JTextField();

                // If the column has a card at that index, write the card ID into the text field
                if (columnsList.get(i).getCards().get(j) != null) {
                    card.setText(String.valueOf(columnsList.get(i).getCards().get(j).getCard().getId()));
                }
                column.add(card);
            }
            mainContainer.add(column);
        }

    }


    /**
     * Translate back into a board panel
     */
    private void submitBoard() {


        for (Component comp : this.getComponents()) {

        }

    }
}
