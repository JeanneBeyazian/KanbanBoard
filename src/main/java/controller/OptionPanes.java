package controller;

import view.boardComponents.BoardPanel;

import javax.swing.*;

/**
 * JOptionPanes displayed to the user on the GUI to warn them of errors, or misuses.
 */
public class OptionPanes {

    /**
     * Response displayed when WIP limit is reached, and no more cards can be added to 'In progress' columns.
     */
    public static void showWIPLimitReachedError(BoardPanel board) {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null,
                "You have reached the Work In Progress limit, set to " + board.getWIPlimit() + ".",
                "WIP Limit Reached", JOptionPane.WARNING_MESSAGE);

    }

    /** Response to an unknown command : shows an error JOptionPane */
    public static void commandNotFoundError(String errorMessage) {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /** Response to failed selection of column from the combo box : shows an error JOptionPane */
    public static void noColumnSelectedError() {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, "There is no column in your board!",
                "Column Not Found", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Response to already empty board when trying to clear */
    public static void boardEmptyError(){
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, "The board is already empty!", "Empty Board",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /** Response to already empty column when trying to clear */
    public static void columnEmptyError(){
        JOptionPane.showMessageDialog(null, "There are no cards in this column!",
                "Empty Column", JOptionPane.INFORMATION_MESSAGE);
    }
}
