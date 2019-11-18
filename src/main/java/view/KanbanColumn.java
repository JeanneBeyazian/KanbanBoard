package view;

import controller.ColumnRole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class KanbanColumn extends JPanel {

    private ArrayList<KanbanCard> cards;
    ColumnRole role;
    JScrollPane columnPane;

    // TODO : choose a proper size for column using constants below
    int WIDTH;
    int LENGTH;

    public KanbanColumn(String columnTitle, ColumnRole role) {

        this.role = role;
        columnPane = new JScrollPane();
        cards = new ArrayList<>();

        //setSize(WIDTH,LENGTH);
        initialiseColumn(columnTitle);
    }

    private void initialiseColumn(String nameIn) {

        JLabel columnName = new JLabel(nameIn);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(columnName);
        add(columnPane);


    }

    private void addCard(KanbanCard card) {
        cards.add(card);
    }

    private void removeCard(KanbanCard card) {
        cards.remove(card);
    }

}
