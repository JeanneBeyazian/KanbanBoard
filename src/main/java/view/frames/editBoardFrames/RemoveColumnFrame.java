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
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Petra",
        creationDate = "29/11/2019",
        lastEdit = "12/12/2019"
)
/**
 * Frame for removing a column from the board
 */
public class RemoveColumnFrame extends EditorFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Frame specific components
	protected JLabel chooseColumnLabel;
    protected JComboBox<String> columnsBox;


    public RemoveColumnFrame(BoardPanel currentPanel) {

        super(currentPanel);
        chooseColumnLabel = new JLabel("Choose a column:");
        columnsBox = createColumnsList();
        submit.addActionListener(this);
        this.setUpFrame();
    }

    /**
     * Set up the frame and its components :
     * add a choose column label and combo box.
     */
    private void setUpFrame() {

        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Removing a column from the board")));

        Map<JComponent, Pair<Integer,Integer>> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(chooseColumnLabel, new Pair<>(0,1)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(columnsBox, new Pair<>(1,1))
        );

        placeComponents(map, 3);

    }

    /**
     * Method triggering the removal of a column.
     * @param event
     */
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
                KanbanColumn col = currentPanel.getColumnByTitle(columnName);
                currentPanel.removeColumn(col);
                //log change

                Change change = new Change(Change.ChangeType.REMOVE, columnName, col);
                ChangeLog.getInstance().addChange(change);


            }
            catch (KanbanObjectNotFoundException e){
                System.out.println("Error: Column not found");
                e.printStackTrace();
                return;
            }
            catch (UnknownKanbanObjectException u){
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
