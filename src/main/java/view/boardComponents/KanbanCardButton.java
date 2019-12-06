package view.boardComponents;

import view.frames.KanbanCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KanbanCardButton extends JPanel {


    private JButton cardButton;
    private static String cardTitle;

	private static final long serialVersionUID = 1L;

    private String buttonTitle;

    private KanbanCard card;
    private KanbanColumn column;

    public KanbanCardButton(KanbanColumn columnIn, String name, String description, int storyPoints) {

        cardButton = createButton(name);
        buttonTitle = name;
        card = new KanbanCard(this, name, description, storyPoints);
        column = columnIn;

        setMaximumSize(new Dimension(195,100));
        setBackground(new java.awt.Color(153, 240, 168));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        add(cardButton);
    }
    
    public KanbanCardButton(KanbanCardButton other) {

        cardButton = createButton(other.getCardButtonTitle());
        buttonTitle = other.getCardButtonTitle();
        String description = other.getCard().getCardDescription();
        int pts = other.getCard().getStoryPoints();
        card = new KanbanCard(this, buttonTitle, description, pts);
        column = other.getColumn();

        setMaximumSize(new Dimension(195,100));
        setBackground(new java.awt.Color(153, 240, 168));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        add(cardButton);
    }

    private JButton createButton(String cardName){

        JButton button = new JButton(cardName);
        button.setPreferredSize(new Dimension(185,90));

        button.addActionListener(new openCardEvent());
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        //button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }

    public String getCardButtonTitle() {
        return buttonTitle;
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

    public void setTitle(String newTitle) {
        cardTitle = newTitle;

    }


    class openCardEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            card.setVisible(true);
        }
    }
}

