package view.frames;

import controller.Load;
import controller.exceptions.KanbanObjectNotFoundException;
import view.KanbanBoard;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadWarningFrame extends PopUpFrames implements ActionListener {

    private KanbanBoard currentBoard;
    /**
     * Constructor - help frame set up.
     */
    public LoadWarningFrame(KanbanBoard board){
        super("Loading Board Warning");
        currentBoard = board;
        buttonPanel.add(createSubmitButton());
    }

    /**
     * Help text with instructions on how to use the Kanban board app in a JTextArea.
     * (Non editable)
     */
    @Override
    protected JTextArea createText()  {

        JTextArea warningText = new JTextArea();
        String text = "WARNING.\n\nAre you sure you want to load an existing board?\n" +
                "If you haven't saved your current progress, the board you're currently working on" +
                " will be erased.\nThis action cannot be undone, and the data cannot be retrieved.\n\n"+
                "Do you want to proceed with this action?";

        warningText.setText(text);
        warningText.setMargin(new Insets(10,10,10,10));
        warningText.setEditable(false);
        return warningText;
    }


    private JButton createSubmitButton() {

        JButton proceed = new JButton("Proceed");
        proceed.addActionListener(this);
        //proceed.setBackground(new java.awt.Color(110, 199, 233));
        proceed.setBorderPainted(false);

        return proceed;
    }


    public void actionPerformed(ActionEvent event) {

            this.dispose();
            if (!currentBoard.getBoard().isEmpty()) currentBoard.getBoard().clearBoard();

            BoardPanel newBoard = Load.loadBoard();
            ArrayList<KanbanColumn> cols = newBoard.getColumns();

            for(int i = 0; i < cols.size(); i++) {
                KanbanColumn col = cols.get(i);
                KanbanColumn boardCol = new KanbanColumn(col.getColumnTitle(), col.getRole());
                ArrayList<KanbanCardButton> cards = cols.get(i).getCards();
                if(cards.size() != 0) {
                    for(int j = 0; j < cards.size(); j++) {
                        //KanbanCardButton cardButton = cards.get(j);
                        KanbanCardButton cardButton = new KanbanCardButton(cards.get(j));

                        boardCol.addCard(cardButton);
                    }
                }
                currentBoard.getBoard().addColumn(boardCol);
            }


    }


}
