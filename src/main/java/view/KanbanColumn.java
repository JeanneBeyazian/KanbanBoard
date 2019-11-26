package view;

import annotations.ClassAnnotation;
import controller.ColumnRole;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Ali & Jeanne"},
        creationDate = "13/11/2019",
        lastEdit = "26/11/2019"
)

public class KanbanColumn extends JPanel {

    // TODO : give a unique ID to each column DONE
    public static final int WIDTH = 300;
    private ArrayList<KanbanCardButton> cards;
    private static int id = -1;
    private ColumnRole role;
    private JScrollPane columnPane;
    private int HEIGHT;

    public KanbanColumn(String columnTitle, ColumnRole role) {
        cards = new ArrayList<>();
        this.role = role;
        ++id;
        columnPane = new JScrollPane();

        //setSize(WIDTH,LENGTH);
        initialiseColumn(columnTitle);
    }
  
    private void initialiseColumn(String nameIn) {

        JLabel ID = new JLabel(String.valueOf(id));
        add(ID);

        setBackground(new java.awt.Color(86, 164, 194));

        JLabel columnName = new JLabel(nameIn);
        columnName.setSize(30,10);
        columnName.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(columnName);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(columnPane);

        addCard(new KanbanCardButton("Card one", "This is the first card", 10));
        addCard(new KanbanCardButton("Card two", "This is the second card", 20));
    }

    private void addCard(KanbanCardButton card) {
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        cards.add(card);
        add(card);
        columnPane.add(card);
    }

    private void removeCard(KanbanCard card) {
    	if(card != null) {
    		card = null;
    	}
    	else {
    		System.out.println("No card to delete brozer");
    	}
    }

    
    private void setHeight(int inHeight) {
    	HEIGHT = inHeight;
    }

    public int getId(){
        return id;
    }
    

}
