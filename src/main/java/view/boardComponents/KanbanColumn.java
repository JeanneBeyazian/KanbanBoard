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
        lastEdit = "05/11/2019"
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
        addButtons();
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

    private void addButtons() {

        //JPanel smallPanel

        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit this column");
        //editButton.addActionListener(e-> new EditColumnFrame);
        editButton.setBackground(new java.awt.Color(110, 199, 233));
        editButton.setBorderPainted(false);

        JButton clearButton = new JButton("Clear");
        clearButton.setToolTipText("Delete all cards from column?");
        clearButton.addActionListener(e->this.clearColumn());
        clearButton.setBackground(new java.awt.Color(250, 105, 128));
        clearButton.setBorderPainted(false);
        //clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(clearButton, BorderLayout.SOUTH);
    }

    public void addCard(KanbanCardButton card) {

        cards.add(card);    // Add to ArrayList
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnPane.add(card);
        revalidate();
        repaint();
        columnPane.add(Box.createRigidArea(new Dimension(0, 10)));

    }

    public void removeCard(KanbanCardButton card) {

    	if(card != null) {
    		cards.remove(card);
            columnPane.remove(card);
            card.setCard(null);
            revalidate();
            repaint();
        }
    	else {
    		System.out.println("The card does not exist.");
    	}
    }

    public BoardPanel getBoard() {
        return (BoardPanel)this.getParent();
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public ColumnRole getRole() { return role;}

    public int getId(){
        return id;
    }

    public ArrayList<KanbanCardButton> getCards() {
        return cards;
    }

    private void clearColumn() {
        if (cards.isEmpty()) {
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "There are no cards in this column!", "Empty Column",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        cards.clear();
        columnPane.removeAll();
        revalidate();
        repaint();
    }
}
