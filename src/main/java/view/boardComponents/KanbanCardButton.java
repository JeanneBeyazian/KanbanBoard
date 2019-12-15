package view.boardComponents;

import annotations.ClassAnnotation;
import controller.exceptions.UnknownKanbanObjectException;
import model.Change;
import model.ChangeLog;
import view.frames.KanbanCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Nathan",
        creationDate = "13/11/2019",
        lastEdit = "15/11/2019"
)

public class KanbanCardButton extends JPanel {

    private static final long serialVersionUID = 1L;

    // Components for panel
    private JLabel buttonTitle;
    private JLabel storyPoints;

    // Internal fields : associated card and column
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

    /**
     * Set up the the KanbanCardButton and its components
     */
    private void initialise() {

        setMinimumSize(new Dimension(195,100));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setBackground(new java.awt.Color(165, 218, 240));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        setLayout(new BorderLayout());

        storyPoints.setSize(new Dimension(195,10));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.setVisible(true);
            }
        });

        add(buttonTitle, BorderLayout.CENTER);
        add(storyPoints, BorderLayout.SOUTH);

    }


    /**
     * Get associated card frame
     * @return card
     */
    public KanbanCard getCard(){
        return card;
    }

    /**
     * Set the card frame to a new one
     * @param cardValue KanbanCard
     */
    public void setCard(KanbanCard cardValue) {
        if (cardValue == null) card.setVisible(false);
        this.card = cardValue;
    }

    /**
     * Get the associated column
     * @return KanbanColumn column
     */
    public KanbanColumn getColumn() {
        return column;
    }

    /**
     * Get the card title
     * @return title
     */
    public String getCardButtonTitle() {
        return buttonTitle.getText();
    }

    /**
     * Reset title with input one
     * @param newTitle
     */
    public void setTitle(String newTitle) {
        buttonTitle.setText(newTitle);
        revalidate();
        repaint();
    }

    /**
     * Reset story points with selected ones from combo box
     * @param newPoints
     */
    public void setStoryPoints(String newPoints) {
        storyPoints.setText(newPoints);
    }

}




