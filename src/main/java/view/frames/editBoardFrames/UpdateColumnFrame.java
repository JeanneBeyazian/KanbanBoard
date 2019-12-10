package view.frames.editBoardFrames;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import controller.exceptions.KanbanObjectNotFoundException;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateColumnFrame extends AddFrame implements ActionListener{

    private JComboBox<String> rolesAvailable;
    private KanbanColumn column;

    public UpdateColumnFrame(BoardPanel board, KanbanColumn column) {
        super("Column", board);
        this.column = column;
        this.setUpFrame();
        submit.addActionListener(this);
    }

    private void setUpFrame() {

        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Editing column " + column.getColumnTitle())));

        // Create JComboBox for roles and its label
        JLabel rolesLabel = new JLabel("Select a role: ");

        rolesAvailable = createRolesList();
        rolesAvailable.setSelectedItem(column.getRole().getColumnRole());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(rolesLabel, constraints);
        constraints.gridx = 1;
        container.add(rolesAvailable, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        container.add(submit, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        container.add(cancel,constraints);

        pack();


    }

    // TODO : put everything in column class
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            String newColumnName = "";

            if (!(titleField.getText() == null || titleField.getText().isEmpty())) newColumnName = titleField.getText();
            else newColumnName = column.getColumnTitle();

            String role = (rolesAvailable.getSelectedItem().toString());
            ColumnRole setRole = null;

            for (ColumnRole enumRole : ColumnRole.values()) {
                if ((enumRole.getColumnRole()).equals(role)) {
                    setRole = enumRole;
                    break;
                }
            }

            if (setRole != null) column.setRole(setRole);
            column.setColumnTitle(newColumnName);

            dispose();

        } else {
            showError("Command not found");
        }

    }


}
