package view.boardComponents;

import view.containers.KanbanCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KanbanCardButton extends JPanel {

    // NOT USED
    private String title;
    private String description;

    private JButton cardButton;
    private String cardTitle;
    private KanbanCard card;
    private KanbanColumn column;

    public KanbanCardButton(KanbanColumn columnIn, String name, String description, int storyPoints) {
        cardButton = createButton(name);
        cardTitle = name;
        card = new KanbanCard(this, name, description, storyPoints);
        column = columnIn;

        setBackground(new java.awt.Color(153, 240, 168));
        setBorder(new EmptyBorder(0,0,0,0));
        setPreferredSize(new Dimension(140,70));
        add(cardButton);
    }

    private JButton createButton(String cardName){

        JButton button = new JButton(cardName);

        button.addActionListener(new openCardEvent());
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        //button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public KanbanCard getCard(){
        return card;
    }

    public void setCard(KanbanCard cardValue){
        if (cardValue == null) card.setVisible(false);
        this.card = cardValue;
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

