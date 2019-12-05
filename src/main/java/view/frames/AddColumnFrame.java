package view.frames;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "28/11/2019",
        lastEdit = "29/11/2019"
)

public class AddColumnFrame extends AddFrame implements ActionListener {

    private String role;
    private JComboBox<String> rolesBox;

    public AddColumnFrame(BoardPanel currentPanel) {

        super("column", currentPanel);
        rolesBox = createRolesList();
        submit.addActionListener(this);
        this.setUpFrame();

    }

    private void setUpFrame(){

        JLabel chooseRole = new JLabel("Choose a role:");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(chooseRole, constraints);
        constraints.gridx = 1;
        container.add(rolesBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        container.add(submit, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        container.add(cancel,constraints);

        pack();

    }

    private JComboBox<String> createRolesList(){
        JComboBox<String> box = new JComboBox<>(ColumnRole.getRoles());
        return box;
    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            String columnTitle = "";

            if (! (titleField.getText() == null || titleField.getText().strip().isEmpty()))  columnTitle = titleField.getText();
            else columnTitle = "Unnamed Column";

            role = String.valueOf(rolesBox.getSelectedItem());
            ColumnRole setRole = null;

            for (ColumnRole enumRole : ColumnRole.values()) {
                if ((enumRole.getColumnRole()).equals(role)) {
                    setRole = enumRole;
                    break;
                }
            }
            currentPanel.addColumn(new KanbanColumn(columnTitle, setRole));
        }
        else {
            showError();
        }

        dispose();

    }
}
