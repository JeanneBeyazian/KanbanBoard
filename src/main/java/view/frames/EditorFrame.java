package view.frames;
import annotations.ClassAnnotation;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "29/11/2019",
        lastEdit = "05/11/2019"
)
/**
 * Editor JFrame to add/delete/modify components on the current BoardPanel.
 * It is a super class used as an extendable template for more specific frames.
 */
abstract public class EditorFrame extends JFrame implements ActionListener {

    protected BoardPanel currentPanel;
    protected JPanel container;             // contains frame components
    protected JButton submit;
    protected JButton cancel;

    public EditorFrame(BoardPanel currentPanel) {
        this.currentPanel = currentPanel;
        createButtons();
        initialise();
    }

    /** Set up of the frame */
    protected void initialise() {
        container = new JPanel(new GridBagLayout());
        add(container);
        pack();
        setLocationRelativeTo(null);
    }

    /** Create the buttons for submission and closing the frame */
    private void createButtons() {
        cancel = new JButton("Cancel");
        submit = new JButton("Submit");
        cancel.addActionListener(e -> this.dispose());
    }

    /**
     * Puts all columns on the board into a comboBox
     * @return columns JComboBox
     */
    protected JComboBox<String> createColumnsList() {

        JComboBox<String> columnsBox = new JComboBox<>();
        ArrayList<KanbanColumn> cols = currentPanel.getColumns();
        for (KanbanColumn column : cols) columnsBox.addItem(column.getColumnTitle());
        if (cols.isEmpty()==true) columnsBox.setEnabled(false); // If no columns on board, can't choose from combobox
        return columnsBox;
    }

    /** Response to an unknown command : shows an error JOptionPane */

    protected void showError(String errorMessage) {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, errorMessage, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /** Response to failed selection of column from the combo box : shows an error JOptionPane */
    protected void noColumnSelectedError() {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, "There is no column in your board!", "Column Not Found",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
