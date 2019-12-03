package view.frames;

import annotations.ClassAnnotation;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "28/11/2019",
        lastEdit = "29/11/2019"
)

public class AddCardFrame extends AddFrame implements ActionListener {

    private JTextArea cardDescriptionArea;
    private JComboBox<Integer> storyPointsBox;
    private JComboBox<String> columnsBox;

    public AddCardFrame(BoardPanel currentPanel) {
        super("card", currentPanel);
        submit.addActionListener(this);
        this.setUpFrame();
    }

    private void setUpFrame() {

        // Set up description area
        cardDescriptionArea = new JTextArea();
        JLabel descriptionLabel = new JLabel("Description:");
        cardDescriptionArea.setRows(5);
        cardDescriptionArea.setColumns(30);

        // Set up story points combo box
        storyPointsBox = new JComboBox<>();
        JLabel pointsLabel = new JLabel("Story Points:");
        int max = 150;
        for (int i = 0; i <= max; i++) {
            storyPointsBox.addItem(i);
        }

        // Set up columns combo box
        columnsBox = createColumnsList();
        JLabel columnLabel = new JLabel("In Column:");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(descriptionLabel, constraints);
        constraints.gridx = 1;
        container.add(cardDescriptionArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        container.add(pointsLabel, constraints);
        constraints.gridx = 1;
        container.add(storyPointsBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        container.add(columnLabel, constraints);
        constraints.gridx = 1;
        container.add(columnsBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
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

            String cardName = "";
            if (!titleField.getText().isBlank())  cardName = titleField.getText();
            else cardName = "Unnamed Card";

            String description = cardDescriptionArea.getText();
            int storyPoints = (int)storyPointsBox.getSelectedItem();
            String columnName = String.valueOf(columnsBox.getSelectedItem());

            KanbanColumn columnToAdd = null;

            for (KanbanColumn col : currentPanel.getColumns()) {
                if (col.getColumnTitle().equals(columnName)) {
                    columnToAdd = col;
                    break;
                }
            }
            columnToAdd.addCard(new KanbanCardButton(columnToAdd,cardName, description,storyPoints));
            dispose();
        }
        else {
            showError();
        }

    }
}
