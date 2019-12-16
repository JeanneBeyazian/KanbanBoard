package view.boardComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DragAndDrop extends JFrame {

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

    private void createDragAndDropUI() {

        ArrayList<KanbanColumn> columnsList = currentBoard.getColumns();
        int numberOfColumns = columnsList.size();

        int maxColSize = 0;
        for (KanbanColumn column : columnsList) {
            int colSize = column.getCards().size();
            if (maxColSize < colSize) maxColSize = colSize;
        }

        for (int i=0; i<numberOfColumns; i++) {

            JPanel column = new JPanel();
            column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
            JLabel colName = new JLabel(columnsList.get(i).getColumnTitle());
            column.add(colName);

            for (int j=0; j<maxColSize; j++){
                JTextField card = new JTextField();

                if (columnsList.get(i).getCards().get(j) != null) {
                    card.setText(columnsList.get(i).getCards().get(j).getCardButtonTitle());
                }
                column.add(card);
            }
            mainContainer.add(column);
        }

    }


    private void submitBoard() {


        for (Component comp : this.getComponents()) {

        }

    }
}
