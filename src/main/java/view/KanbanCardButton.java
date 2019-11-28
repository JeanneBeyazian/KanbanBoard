package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KanbanCardButton extends JButton {
    private String title;
    private String description;

    private String cardTitle;
    private KanbanCard card;
    private KanbanColumn column;

    public KanbanCardButton(KanbanColumn column, String name, String description, int storyPoints) {
        super(name);
        this.column=column;
        addActionListener(new openCardEvent());
        setBackground(new java.awt.Color(153, 240, 168));
        cardTitle = name;
        card = new KanbanCard(this, name, description, storyPoints);
    }


    public String getCardTitle() {
        return cardTitle;
    }

    public KanbanCard getCard(){
        return card;
    }

    public KanbanColumn getColumn() {
        return column;
    }

    class openCardEvent implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            card.setVisible(true);
        }
    }
}
