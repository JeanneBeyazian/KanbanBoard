package view.boardComponents;

import annotations.ClassAnnotation;
import view.frames.KanbanCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@ClassAnnotation(
        classAuthors = {"Jeanne & Nathan"},
        creationDate = "13/11/2019",
        lastEdit = "08/11/2019"
)

public class KanbanCardButton extends JPanel {


    private JButton cardButton;

    private static final long serialVersionUID = 1L;

    private String buttonTitle;
    private JLabel storyPoints;
    private KanbanCard card;
    private KanbanColumn column;


    public KanbanCardButton(KanbanColumn columnIn, String name, String description, int storyPoints) {

        cardButton = createButton(name);
        buttonTitle = name;
        card = new KanbanCard(this, name, description, storyPoints);
        column = columnIn;
        this.storyPoints = new JLabel(String.valueOf(storyPoints));

        initialise(cardButton);
    }

    /**
     * Copy constructor
     * @param other KanbanCardButton to be copied
     */
    public KanbanCardButton(KanbanCardButton other) {

        cardButton = createButton(other.getCardButtonTitle());
        buttonTitle = other.getCardButtonTitle();
        String description = other.getCard().getCardDescription();
        int pts = other.getCard().getStoryPoints();
        card = new KanbanCard(this, buttonTitle, description, pts);
        column = other.getColumn();

        initialise(cardButton);
    }

    private void initialise(JButton cardButton) {

        setMaximumSize(new Dimension(195,100));
        setBackground(new java.awt.Color(165, 218, 240));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        storyPoints.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.setVisible(true);
            }
        });

        add(cardButton);
        add(storyPoints);
    }

    private JButton createButton(String cardName){

        JButton button = new JButton(cardName);
        button.setPreferredSize(new Dimension(185,90));
        button.addActionListener(e->card.setVisible(true));
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

    public void setCard(KanbanCard cardValue) {
        if (cardValue == null) card.setVisible(false);
        this.card = cardValue;
    }

    public KanbanColumn getColumn() {
        return column;
    }

    public void setTitle(String newTitle) {
        cardButton.setText(newTitle);
        revalidate();
        repaint();
    }

}



