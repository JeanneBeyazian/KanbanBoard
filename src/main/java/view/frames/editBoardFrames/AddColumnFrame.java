package view.frames.editBoardFrames;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import controller.OptionPanes;
import javafx.util.Pair;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Map;

import static controller.OptionPanes.commandNotFoundError;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Petra",
        creationDate = "28/11/2019",
        lastEdit = "12/12/2019"
)

public class AddColumnFrame extends AddFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;
    private JComboBox<String> rolesBox;

    public AddColumnFrame(BoardPanel currentPanel) {

        super("column", currentPanel);
        rolesBox = createRolesList();
        submit.addActionListener(this);
        setName("addColumnFrame");
        this.setUpFrame();

    }

    private void setUpFrame(){

        JLabel chooseRole = new JLabel("Choose a role:");

        Map<JComponent, Pair<Integer,Integer>> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(titleLabel, new Pair<>(0,0)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(titleField, new Pair<>(1,0)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(chooseRole, new Pair<>(0,2)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(rolesBox, new Pair<>(1,2))
        );

        placeComponents(map, 3);


    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            String columnTitle = "";

            if (! (titleField.getText() == null || titleField.getText().isEmpty()))  columnTitle = titleField.getText();
            else columnTitle = "Unnamed Column";

            role = String.valueOf(rolesBox.getSelectedItem());
            ColumnRole setRole = null;

            for (ColumnRole enumRole : ColumnRole.values()) {
                if ((enumRole.getColumnRole()).equals(role)) {
                    setRole = enumRole;
                    break;
                }
            }
            currentPanel.addColumn(new KanbanColumn(columnTitle, setRole, currentPanel));
        }
        else {
            commandNotFoundError();
        }

        dispose();

    }
}
