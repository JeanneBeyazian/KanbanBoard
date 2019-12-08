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

    private static final long serialVersionUID = 1L;

    private JLabel buttonTitle;
    private JLabel storyPoints;
    private KanbanCard card;
    private KanbanColumn column;


    public KanbanCardButton(KanbanColumn columnIn, String name, String description, int storyPoints) {

        buttonTitle = new JLabel(name);
        card = new KanbanCard(this, name, description, storyPoints);
        column = columnIn;
        this.storyPoints = new JLabel(String.valueOf(storyPoints));

        initialise();
    }

    /**
     * Copy constructor
     * @param other KanbanCardButton to be copied
     */
    public KanbanCardButton(KanbanCardButton other) {

        buttonTitle = new JLabel(other.getCardButtonTitle());
        String description = other.getCard().getCardDescription();
        int points = other.getCard().getStoryPoints();
        card = new KanbanCard(this, getCardButtonTitle(), description, points);
        column = other.getColumn();
        storyPoints = new JLabel(String.valueOf(points));

        initialise();
    }

    private void initialise() {

        setMaximumSize(new Dimension(195,100));
        setBackground(new java.awt.Color(165, 218, 240));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        storyPoints.setSize(new Dimension(195,10));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.setVisible(true);
            }
        });

        add(buttonTitle, BorderLayout.CENTER);
        add(storyPoints, BorderLayout.SOUTH);


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

    public String getCardButtonTitle() {
        return buttonTitle.getText();
    }

    public void setTitle(String newTitle) {
        //cardButton.setText(newTitle);
        buttonTitle.setText(newTitle);
        revalidate();
        repaint();
    }

}




