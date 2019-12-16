package view.frames.editBoardFrames;

import annotations.ClassAnnotation;
import controller.OptionPanes;
import controller.exceptions.KanbanObjectNotFoundException;
import javafx.util.Pair;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Map;

import static controller.OptionPanes.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Petra",
        creationDate = "28/11/2019",
        lastEdit = "15/12/2019"
)
/**
 * Frame for adding a card to the board
 */
public class AddCardFrame extends AddFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Frame specific components
	private JTextArea cardDescriptionArea;
    private JComboBox<Integer> storyPointsBox;
    private JComboBox<String> columnsBox;


    public AddCardFrame(BoardPanel currentPanel) {

        super("card", currentPanel);
        submit.addActionListener(this);
        setName("addCardFrame");
        this.setUpFrame();

    }

    /**
     * Set up the frame and its components :
     *      - title label and field,
     *      - description label and area,
     *      - story points label and combo box,
     *      - column label and combo box.
     */
    private void setUpFrame() {

        titleField.addActionListener(this);

        // Set up description area
        cardDescriptionArea = new JTextArea();
        cardDescriptionArea.setName("cardDescriptionArea");

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setName("descriptionLabel");

        cardDescriptionArea.setRows(5);
        cardDescriptionArea.setColumns(30);

        // Set up story points combo box
        storyPointsBox = new JComboBox<>();
        JLabel pointsLabel = new JLabel("Story Points:");
        int max = 150;
        for (int i = 0; i <= max; i++) storyPointsBox.addItem(i);
        storyPointsBox.setName("storyPointsBox");

        // Set up columns combo box
        columnsBox = createColumnsList();
        JLabel columnLabel = new JLabel("In Column:");
        columnLabel.setName("chooseColumnLabel");

        Map<JComponent, Pair<Integer,Integer>> map = Map.ofEntries(
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(titleLabel, new Pair<>(0,0)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(titleField, new Pair<>(1,0)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(descriptionLabel, new Pair<>(0,2)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(cardDescriptionArea, new Pair<>(1,2)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(pointsLabel, new Pair<>(0,5)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(storyPointsBox, new Pair<>(1,5)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(columnLabel, new Pair<>(0,6)),
                new AbstractMap.SimpleEntry<JComponent, Pair<Integer,Integer>>(columnsBox, new Pair<>(1,6))
        );

        placeComponents(map, 7);

    }


    /**
     * Action performed when adding a card to the board
     * @param event
     */
    public void actionPerformed(ActionEvent event) {

        if (checkEvenSource(event)) {

            if(!columnsBox.isEnabled()) {
                missingComponentError("Column");
                return;
            }

            String cardName = "";

            if (!(titleField.getText() == null || titleField.getText().isEmpty())) cardName = titleField.getText();
            else cardName = "Unnamed Card";

            String description = cardDescriptionArea.getText();
            int storyPoints = (int)storyPointsBox.getSelectedItem();
            String columnName = String.valueOf(columnsBox.getSelectedItem());

            KanbanColumn columnToAdd = null;

            try {
                columnToAdd = currentPanel.getColumnByTitle(columnName);
            }

            catch (KanbanObjectNotFoundException e) {
                OptionPanes.commandNotFoundError();
                System.out.println("Error: Column not found");
                e.printStackTrace();
                return;
            }

            columnToAdd.addCard(new KanbanCardButton(columnToAdd,cardName, description,storyPoints));
            dispose();
        }
        else {
            commandNotFoundError();
        }

    }
}
