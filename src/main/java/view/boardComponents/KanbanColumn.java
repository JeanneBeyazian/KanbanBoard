package view.boardComponents;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import view.containers.ScrollContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Ali & Jeanne"},
        creationDate = "13/11/2019",
        lastEdit = "27/11/2019"
)

public class KanbanColumn extends JPanel {

    private ArrayList<KanbanCardButton> cards;
    private static int id = -1;
    private ColumnRole role;
    private ScrollContainer columnPane;

    public KanbanColumn(String columnTitle, ColumnRole role) {
        cards = new ArrayList<>();
        this.role = role;
        ++id;
        columnPane = new ScrollContainer();
        setPreferredSize(new Dimension(200,710));
        initialiseColumn(columnTitle);
    }
  
    private void initialiseColumn(String nameIn) {

        JLabel ID = new JLabel(String.valueOf(id));
        ID.setForeground(Color.lightGray);
        add(ID);

        setBackground(new java.awt.Color(26, 58, 161));

        JLabel columnName = new JLabel(nameIn);
        columnName.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnName.setForeground(Color.lightGray);
        add(columnName);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(columnPane);

        // TESTING PURPOSE : ADDS A SINGLE CARD TO THE COLUMN
        KanbanCardButton testing = new KanbanCardButton("name", "description", 10);
        addCard(testing);
        removeCard(testing);

        // TESTING PURPOSE : ADDS 50 CARDS TO A COLUMN
        for (int i=0; i<2; i++){
            addCard(new KanbanCardButton(("Card "+ i), "Description for card", i+10));
        }

    }

    private void addCard(KanbanCardButton card) {
        cards.add(card);    // Add to ArrayList
        JPanel cardContainer = new JPanel();
        //cardContainer.setPreferredSize(new Dimension(170,0));
        //cardContainer.setAlignmentY(JPanel.TOP_ALIGNMENT);

        cardContainer.add(card);
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnPane.add(cardContainer);
        columnPane.add(Box.createRigidArea(new Dimension(0, 10)));

    }

    private void removeCard(KanbanCardButton card) {
    	if(card != null) {
    		cards.remove(card);
    		columnPane.remove(card);
            card = null;
        }
    	else {
    		System.out.println("No card to delete brozer");
    	}
    }


    public int getId(){
        return id;
    }
    

}
