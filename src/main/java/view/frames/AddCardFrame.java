package view.frames;

import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCardFrame extends AddFrame implements ActionListener {

    private JTextArea cardDescriptionArea;
    private JComboBox<Integer> storyPointsBox;
    private JComboBox<String> columnsBox;

    public AddCardFrame(BoardPanel currentPanel) {
        super("card", currentPanel);
        submit.addActionListener(this);
        setUpFrame();
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
        columnsBox = new JComboBox<>();
        JLabel columnLabel = new JLabel("In Column:");
        ArrayList<KanbanColumn> cols = currentPanel.getColumns();
        if (cols.isEmpty()==true) columnsBox.setEnabled(false);
        for (KanbanColumn column : cols) {
            columnsBox.addItem(column.getColumnTitle());
        }

        // Remove temporarily
        container.remove(submit);
        container.remove(cancel);

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

    private void noColumnSelectedError(){
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, "There is no column in your board!", "Column Not Found",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {
            System.out.println("UUH");

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
            columnToAdd.addCard(new KanbanCardButton(columnToAdd,columnName, description,storyPoints));
            dispose();
        }
        else {
            showError();
        }

    }
}
