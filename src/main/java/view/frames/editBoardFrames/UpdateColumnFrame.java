package view.frames.editBoardFrames;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import controller.exceptions.KanbanObjectNotFoundException;
import javafx.util.Pair;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Map;

import static controller.OptionPanes.commandNotFoundError;

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

        Map<JComponent, Pair<Integer,Integer>> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(titleLabel, new Pair<>(0,0)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(titleField, new Pair<>(1,0)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(rolesLabel, new Pair<>(0,1)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(rolesAvailable, new Pair<>(1,1))
        );

        placeComponents(map, 2);


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
            commandNotFoundError();
        }

    }


}
