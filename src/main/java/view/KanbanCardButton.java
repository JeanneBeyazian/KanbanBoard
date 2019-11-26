package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KanbanCardButton extends JButton {

    private String cardTitle;
    private KanbanCard card;

    public KanbanCardButton(String name, String description, int storyPoints) {
        super(name);
        addActionListener(new openCardEvent());
        setBackground(new java.awt.Color(153, 240, 168));
        cardTitle = name;
        card = new KanbanCard(name, description, storyPoints);
    }


    public String getCardTitle() {
        return cardTitle;
    }

    public KanbanCard getCard(){
        return card;
    }

    class openCardEvent implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            card.setVisible(true);
        }
    }
}




