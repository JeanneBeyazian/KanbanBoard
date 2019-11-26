package view;

import javax.swing.*;

public class KanbanCardButton extends JButton {

    private String cardTitle;
    private KanbanCard card;

    public KanbanCardButton(String name, String description, int storyPoints) {
        super(name);
        setBackground(new java.awt.Color(153, 240, 168));
        cardTitle=name;
        card = new KanbanCard(name, description, storyPoints);
    }


    public String getCardTitle() {
        return cardTitle;
    }

    public KanbanCard getCard(){
        return card;
    }
}
