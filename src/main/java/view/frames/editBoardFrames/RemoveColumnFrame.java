package view.frames.editBoardFrames;
import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import javafx.util.Pair;
import model.Change;
import model.ChangeLog;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanColumn;
import view.frames.KanbanCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Map;

import static controller.OptionPanes.commandNotFoundError;
import static controller.OptionPanes.missingComponentError;

@ClassAnnotation(
        classAuthors = {"Jeanne", "Petra"},
        creationDate = "29/11/2019",
        lastEdit = "12/12/2019"
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

        Map<JComponent, Pair<Integer,Integer>> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(chooseColumnLabel, new Pair<>(0,2)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(columnsBox, new Pair<>(1,2))
        );

        placeComponents(map, 3);

    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            if(!columnsBox.isEnabled()) {
                missingComponentError("Column");
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
                Change change = new Change(Change.ChangeType.REMOVE, columnName, this);
                ChangeLog.getInstance().addChange(change);
            } catch (UnknownKanbanObjectException u){
                System.out.println("Failed to log.");
                u.printStackTrace();
            }

            dispose();
        }

        else {
            commandNotFoundError();
        }

    }


}
