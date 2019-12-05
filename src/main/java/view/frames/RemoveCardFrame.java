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
        creationDate = "29/11/2019",
        lastEdit = "05/11/2019"
)
public class RemoveCardFrame extends RemoveColumnFrame implements ActionListener {

    private JLabel chooseCardLabel;
    private JComboBox<String> chooseCardBox;
    private JButton columnChosenButton;


    public RemoveCardFrame(BoardPanel currentPanel) {

        super(currentPanel);
        submit.addActionListener(this);
        addCardBox();
    }

    private void addCardBox() {

        chooseCardBox = new JComboBox<String>();
        chooseCardLabel = new JLabel("Choose a card:");
        columnChosenButton = new JButton("Verify");
        columnChosenButton.addActionListener(e->createCardList());

        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Removing a card from the board")));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(chooseCardLabel, constraints);
        constraints.gridx = 1;
        container.add(chooseCardBox, constraints);
        constraints.gridy = 0;
        constraints.gridx = 3;
        container.add(columnChosenButton, constraints);
        container.remove(cancel);
        constraints.gridy = 3;
        container.add(cancel, constraints);

        pack();
    }

    private KanbanColumn getSelectedColumn() {

        KanbanColumn toReturn = null;

        for (KanbanColumn col : currentPanel.getColumns()) {
            if (col.getColumnTitle().equals(columnsBox.getSelectedItem())) {
                toReturn = col;
                break;
            }
        }

        return toReturn;
    }



    private void createCardList() {

        if (getSelectedColumn() == null) {
            noColumnSelectedError();
            chooseCardBox.setEnabled(false);
            return;
        }

        ArrayList<KanbanCardButton> cards = getSelectedColumn().getCards();

        for (KanbanCardButton cardButton : cards) {
            chooseCardBox.addItem(cardButton.getCardButtonTitle());
        }

        if (cards.isEmpty()==true) columnsBox.setEnabled(false);

        revalidate();
    }



    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == submit) {

            if(!columnsBox.isEnabled() || !chooseCardBox.isEnabled()) {
                return;
            }

            KanbanColumn col = getSelectedColumn();
            KanbanCardButton toRemove = null;
            String cardName = String.valueOf(columnsBox.getSelectedItem());

            for (KanbanCardButton card : col.getCards()){
                if (cardName.equals(card.getCardButtonTitle())) toRemove = card;
            }

            col.removeCard(toRemove);
            currentPanel.repaint();
            dispose();
        }

        else {
            showError();
        }

    }

}
