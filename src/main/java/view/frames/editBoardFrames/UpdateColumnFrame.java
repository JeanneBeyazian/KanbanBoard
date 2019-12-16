package view.frames.editBoardFrames;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import model.Coordinates;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Map;

import static controller.OptionPanes.commandNotFoundError;
@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Ali",
        creationDate = "06/12/2019",
        lastEdit = "15/12/2019"
)
/**
 * Frame for updating a column's information
 */
public class UpdateColumnFrame extends AddFrame implements ActionListener{

    private JComboBox<String> rolesAvailable;
    private KanbanColumn column;

    public UpdateColumnFrame(BoardPanel board, KanbanColumn column) {

        super("Column", board);
        this.column = column;
        this.setUpFrame();
        submit.addActionListener(this);
    }

    /**
     * Sets up the constituents of the frame :
     * title field and label, roles field and combo box
     */
    private void setUpFrame() {

        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Editing column " + column.getColumnTitle())));

        // Create JComboBox for roles and its label
        JLabel rolesLabel = new JLabel("Select a role: ");

        rolesAvailable = createRolesList();
        rolesAvailable.setSelectedItem(column.getRole().getColumnRole());

        Map<JComponent, Coordinates> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<JComponent, Coordinates>(titleLabel, new Coordinates(0,0)),
                new AbstractMap.SimpleEntry<JComponent, Coordinates>(titleField, new Coordinates(1,0)),
                new AbstractMap.SimpleEntry<JComponent, Coordinates>(rolesLabel, new Coordinates(0,1)),
                new AbstractMap.SimpleEntry<JComponent, Coordinates>(rolesAvailable, new Coordinates(1,1))
        );

        placeComponents(map, 2);


    }

    /**
     * Action performed : updates the column by getting all the changed fields
     * @param event
     */
    public void actionPerformed(ActionEvent event) {

        // Make sure the source is known
        if (event.getSource() == submit) {

            String newColumnName = "";

            // If nothing has been entered in the titleField, keep the already set one
            if (!(titleField.getText() == null || titleField.getText().isEmpty())) newColumnName = titleField.getText();
            else newColumnName = column.getColumnTitle();

            String role = (rolesAvailable.getSelectedItem().toString());
            ColumnRole setRole = null;

            // Find the column role slected
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
