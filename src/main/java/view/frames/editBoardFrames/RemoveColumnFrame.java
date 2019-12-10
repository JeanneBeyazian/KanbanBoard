package view.frames.editBoardFrames;
import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import model.Change;
import model.ChangeLog;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;
import view.frames.KanbanCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.OptionPanes.commandNotFoundError;
import static controller.OptionPanes.noColumnSelectedError;

@ClassAnnotation(
        classAuthors = {"Jeanne", "Petra"},
        creationDate = "29/11/2019",
        lastEdit = "08/12/2019"
)

public class RemoveColumnFrame extends EditorFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected JLabel chooseColumnLabel;
    protected  JComboBox<String> columnsBox;
    protected String type = "column";

    public RemoveColumnFrame(BoardPanel currentPanel) {

        super(currentPanel);
        chooseColumnLabel = new JLabel("Choose a column:");
        columnsBox = createColumnsList();
        submit.addActionListener(this);
        this.setUpFrame();
    }

    private void setUpFrame() {
        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Removing a column from the board")));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(chooseColumnLabel, constraints);
        constraints.gridx = 1;
        container.add(columnsBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        container.add(submit, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        container.add(cancel,constraints);

        pack();
    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            if(!columnsBox.isEnabled()) {
                noColumnSelectedError();
                return;
            }
            if(!submit.isValid()){
                return;
            }

            String columnName = String.valueOf(columnsBox.getSelectedItem());

            try {
                currentPanel.removeColumn(currentPanel.getColumnByTitle(columnName));

            }
            catch (KanbanObjectNotFoundException e){
                System.out.println("Error: Column not found");
                e.printStackTrace();
                return;
            }

            //log change
            try {
                Change change = new Change(Change.ChangeType.REMOVE, columnName, KanbanColumn.class);
                ChangeLog.getInstance().addChange(change);
            } catch (UnknownKanbanObjectException u){
                System.out.println("Failed to log.");
                u.printStackTrace();
            }

            dispose();
        }

        else {
            commandNotFoundError("Command not found");
        }

    }


}
