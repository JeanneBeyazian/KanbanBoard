package view.frames;
import annotations.ClassAnnotation;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "29/11/2019",
        lastEdit = "29/11/2019"
)

abstract public class EditorFrame extends JFrame implements ActionListener {

    protected BoardPanel currentPanel;
    protected JPanel container;
    protected JButton submit;
    protected JButton cancel;


    public EditorFrame(BoardPanel currentPanel) {
        this.currentPanel = currentPanel;
        createButtons();
        initialise();
    }

    protected void initialise() {

        container = new JPanel(new GridBagLayout());
        add(container);
        pack();
        setLocationRelativeTo(null);
    }

    private void createButtons() {
        cancel = new JButton("Cancel");
        submit = new JButton("Submit");
        cancel.addActionListener(e -> this.dispose());
    }

    protected JComboBox<String> createColumnsList() {

        JComboBox<String> columnsBox = new JComboBox<>();
        ArrayList<KanbanColumn> cols = currentPanel.getColumns();
        for (KanbanColumn column : cols) {
            columnsBox.addItem(column.getColumnTitle());
        }
        if (cols.isEmpty()==true) columnsBox.setEnabled(false);

        return columnsBox;
    }

    protected void showError() {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, "Command Not Found", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    protected void noColumnSelectedError() {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, "There is no column in your board!", "Column Not Found",
                JOptionPane.INFORMATION_MESSAGE);

    }
}
