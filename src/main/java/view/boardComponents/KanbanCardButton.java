package view.boardComponents;

import view.containers.KanbanCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KanbanCardButton extends JButton {

    private String cardTitle;
    private KanbanCard card;

    public KanbanCardButton(String name, String description, int storyPoints) {
        super(name);
        cardTitle = name;
        card = new KanbanCard(name, description, storyPoints);

        addActionListener(new openCardEvent());
        setBackground(new java.awt.Color(19, 96, 99));
        setBorder(new EmptyBorder(0,0,0,0));
        setPreferredSize(new Dimension(140,70));
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




