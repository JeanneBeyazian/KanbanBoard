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

    public static final int WIDTH = 300;
    private ArrayList<KanbanCardButton> cards;
    private static int id = -1;
    private ColumnRole role;
    private ScrollContainer columnPane;
    private int HEIGHT;

    public KanbanColumn(String columnTitle, ColumnRole role) {

        cards = new ArrayList<>();
        this.role = role;
        ++id;
        columnPane = new ScrollContainer();
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


        // TESTING PURPOSE : ADDS A SINGLE CARD TO THE COLUMN
//        KanbanCardButton testing = new KanbanCardButton("name", "description", 10);
//        addCard(testing);
//        removeCard(testing);

        // TESTING PURPOSE : ADDS 50 CARDS TO A COLUMN
        for (int i=0; i<50; i++){
            addCard(new KanbanCardButton(this, ("Card "+ i), "Description for card", i+10));
        // TESTING PURPOSE : ADDS A SINGLE CARD TO THE COLUMN
        KanbanCardButton testing = new KanbanCardButton(this,"name", "description", 10);
        addCard(testing);
        removeCard(testing);

        }

    }

    public void addCard(KanbanCardButton card) {
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        cards.add(card);    // Add to ArrayList
        add(card);
        columnPane.add(card);
    }

    public void removeCard(KanbanCardButton card) {
    	if(card != null) {
    		cards.remove(card);
    		columnPane.remove(card);
            card = null;
            revalidate();
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
