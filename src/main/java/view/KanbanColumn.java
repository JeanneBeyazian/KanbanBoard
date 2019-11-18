package view;

import controller.ColumnRole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class KanbanColumn extends JPanel {

    // TODO : give a unique ID to each column
    // TODO : choose a proper size for column using constants below
    // TODO (J) : the columnPane (scrollPane) should have one container in which the cards are added.
    // TODO (J) : the container should keep a list of buttons pointing to cards

    String id;
    ColumnRole role;
    JScrollPane columnPane;
    int WIDTH;
    int LENGTH;

    public KanbanColumn(String columnTitle, ColumnRole role) {

        this.role = role;
        columnPane = new JScrollPane();

        //setSize(WIDTH,LENGTH);
        initialiseColumn(columnTitle);
    }

    private void initialiseColumn(String nameIn) {

        setBackground(new java.awt.Color(86, 164, 194));

        JLabel columnName = new JLabel(nameIn);
        columnName.setSize(30,10);
        columnName.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(columnName);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(columnPane);

        addCard(new KanbanCard("Card one", "This is the first card", 10));
        addCard(new KanbanCard("Card two", "This is the second card", 20));
    }

    private void addCard(KanbanCard card) {
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(card);
        //columnPane.add(card);
    }

    private void removeCard(KanbanCard card) {
    }

}
