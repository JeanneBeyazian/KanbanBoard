package view;

import javax.swing.*;
import java.util.ArrayList;

public class KanbanColumn extends JPanel {
    private ArrayList<KanbanCard> cards;

    public KanbanColumn(String nameIn) {

        cards = new ArrayList<>();
        setSize(150,600);
        initialiseColumn(nameIn);
    }


    private void initialiseColumn(String nameIn) {

        JLabel columnName = new JLabel(nameIn);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane columnPane = new JScrollPane();

        // testing purpose
        for (int i = 1; i <=10; i++) {
            columnPane.add(new JButton(" Press " + i));
        }

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
