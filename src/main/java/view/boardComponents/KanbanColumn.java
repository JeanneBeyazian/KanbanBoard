package view.boardComponents;

import annotations.ClassAnnotation;
import controller.ColumnRole;
import view.containers.ScrollContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Ali, Nathan & Jeanne"},
        creationDate = "13/11/2019",
        lastEdit = "28/11/2019"
)

public class KanbanColumn extends JPanel {

    private ArrayList<KanbanCardButton> cards;
    private static int id = -1;

    private String columnTitle;
    private ColumnRole role;
    private ScrollContainer columnPane;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 710;

    public KanbanColumn(String columnTitle, ColumnRole role) {

        cards = new ArrayList<>();
        this.role = role;
        this.columnTitle = columnTitle;
        ++id;
        columnPane = new ScrollContainer();
        initialiseColumn(columnTitle);
    }
  
    private void initialiseColumn(String nameIn) {

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addColumnName(nameIn);
        add(columnPane);

    }

    private void addColumnName(String nameIn) {

        JLabel columnName = new JLabel(nameIn);
        columnName.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnName.setForeground(Color.lightGray);
        columnName.setBackground(new java.awt.Color(26, 58, 161));
        columnName.setOpaque(true);
        columnName.setMaximumSize(new Dimension(200,20));
        add(columnName);

    }

    public void addCard(KanbanCardButton card) {

        cards.add(card);    // Add to ArrayList

        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnPane.add(card);
        revalidate();
        columnPane.add(Box.createRigidArea(new Dimension(0, 10)));

    }

    public void removeCard(KanbanCardButton card) {

    	if(card != null) {
    		cards.remove(card);
            revalidate();
            columnPane.remove(card);
            card.setCard(null);
            revalidate();
            repaint();
        }
    	else {
    		System.out.println("The card does not exist.");
    	}
    }
    
    

    public ColumnRole getRole() {
		return role;
	}

	public void setRole(ColumnRole role) {
		this.role = role;
	}

	public String getColumnTitle() {
        return columnTitle;
    }

    public int getId(){
        return id;
    }

    public ArrayList<KanbanCardButton> getCards() {
        return cards;
    }
}
