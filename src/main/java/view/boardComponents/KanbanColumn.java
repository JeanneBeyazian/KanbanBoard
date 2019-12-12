package view.boardComponents;

import annotations.ClassAnnotation;

import controller.ColumnRole;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import model.Change;
import model.ChangeLog;
import view.containers.ScrollContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Jeanne, (Ali, Nathan, Petra)"},
        creationDate = "13/11/2019",
        lastEdit = "08/12/2019"
)

public class KanbanColumn extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<KanbanCardButton> cards;      // List of cards in column
    private static int id = -1;                     // Unique ID

    // Column components
    private String columnTitle;
    private ColumnRole role;
    private ScrollContainer columnPane;

    private static final int WIDTH = 200;
    private static final int HEIGHT = 710;

    public KanbanColumn(String columnTitle, ColumnRole role) {
        // track change
        try {
            Change change = new Change(Change.ChangeType.ADD, columnTitle, this);
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }

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
        columnName.setBackground(role.getColumnColour());
        columnName.setOpaque(true);
        columnName.setMaximumSize(new Dimension(200,30));
        add(columnName);

    }

    private void addButtons() {

        JPanel smallPanel = new JPanel();
        smallPanel.setMaximumSize(new Dimension(WIDTH, 30));

        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit this column");
        //editButton.addActionListener(e-> new EditColumnFrame().setVisible(true));
        editButton.setBackground(new java.awt.Color(110, 199, 233));
        editButton.setBorderPainted(false);

        JButton clearButton = new JButton("Clear");
        clearButton.setToolTipText("Delete all cards from column?");
        clearButton.addActionListener(e->this.clearColumn());
        clearButton.setBackground(new java.awt.Color(250, 105, 128));
        clearButton.setBorderPainted(false);
        smallPanel.add(editButton);
        smallPanel.add(clearButton);

        add(smallPanel, BorderLayout.SOUTH);

    }

    public void addCard(KanbanCardButton card) {

        BoardPanel board = getBoard();

    	Command addNewCard = new Command("add card", card);
    	//getBoard().addCommand(addNewCard);

        if (role == ColumnRole.IN_PROGRESS &&
                (board.getWIPcount()+card.getCard().getStoryPoints() > board.getWIPlimit())) {
            showWIPLimitReachedError();
            return;
        }

        if (role == ColumnRole.IN_PROGRESS) board.incrementWIPCount(card.getCard().getStoryPoints());
        cards.add(card);    // Add to ArrayList
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        columnPane.add(card);
        revalidate();
        repaint();
        columnPane.add(Box.createRigidArea(new Dimension(0, 10)));

    }

    public void removeCard(KanbanCardButton card) throws KanbanObjectNotFoundException{
    	
    	if(card != null) {
            if (role == ColumnRole.IN_PROGRESS) getBoard().decrementWIPCount(card.getCard().getStoryPoints());
            cards.remove(card);
            columnPane.remove(card);
            card.setCard(null);
            revalidate();
            repaint();
        }
    	else {
    	    throw new KanbanObjectNotFoundException(KanbanCardButton.class, id);
    	}
    }
    
    

    public ColumnRole getRole() {
		return role;
	}

	public void setRole(ColumnRole role) {

		this.role = role;

        // track change
        try {
            Change change = new Change(Change.ChangeType.UPDATE, columnTitle, KanbanColumn.class,
                    "role", role.name());
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }
	}


	public void setColumnTitle(String title) {

        this.columnTitle = title;

        // track change
        try {
            Change change = new Change(Change.ChangeType.UPDATE, columnTitle, KanbanColumn.class, "title", title);
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }
    }

	public String getColumnTitle() {
        return columnTitle;
    }

    public BoardPanel getBoard() {
        return (BoardPanel)this.getParent();
    }

    public int getId(){
        return id;
    }

    public ArrayList<KanbanCardButton> getCards() {
        return cards;
    }

    /**
     *  Get card having a given title
     * @param title title of the card we're searching for
     * @return reference to card
     * @throws KanbanObjectNotFoundException
     */
    public KanbanCardButton getCardByTitle(String title) throws KanbanObjectNotFoundException {
        for (KanbanCardButton card : cards) {
            if (card.getCardButtonTitle().equals(title)) {
                return card;
            }
        }
        throw new KanbanObjectNotFoundException(KanbanCardButton.class);
    }

    /**
     * Remove all cards form a column at once
     */
    private void clearColumn() {

        // If called on empty column
        if (cards.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no cards in this column!", "Empty Column",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        cards.clear();
        columnPane.removeAll();
        revalidate();
        repaint();
    }

    /**
     * Dialog displayed when the WIP limit is reached, and no more cards can be added to an 'In progress' column.
     */
    private void showWIPLimitReachedError() {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null,
                "You have reached the Work In Progress limit, set to " + getBoard().getWIPlimit() + ".",
                "WIP Limit Reached",
                JOptionPane.WARNING_MESSAGE);

    }

}
