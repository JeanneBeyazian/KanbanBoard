package view.frames;

import controller.ColumnRole;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddColumnFrame extends AddFrame implements ActionListener {

    private String role;
    private JComboBox<String> rolesBox;

    public AddColumnFrame(BoardPanel currentPanel) {

        super("column", currentPanel);
        submit.addActionListener(this);
        rolesBox = createRolesList();
        setUpFrame();

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

        pack();

    }

    private JComboBox<String> createRolesList(){
        JComboBox<String> box = new JComboBox<>(ColumnRole.getRoles());
        return box;
    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            String columnTitle = "";
            if (!titleField.getText().isBlank())  columnTitle = titleField.getText();
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
