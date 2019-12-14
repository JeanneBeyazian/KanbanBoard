package view.boardComponents;

import annotations.ClassAnnotation;

import controller.ColumnRole;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import model.Change;
import model.ChangeLog;
import model.DragGestureHandler;
import model.DropHandler;
import view.containers.ScrollContainer;
import view.frames.editBoardFrames.UpdateColumnFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.util.ArrayList;

import static controller.OptionPanes.errorPane;


@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "Jeanne, Ali, Nathan, Petra",
        creationDate = "13/11/2019",
        lastEdit = "14/12/2019"
)
/**
 * This class implements the functionalities of a column in a Kanban Board.
 */
public class KanbanColumn extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<KanbanCardButton> cards;      // List of cards in column
    private static int id = -1;                     // Unique ID

    // Column components
    private JLabel titleLabel;
    private ColumnRole role;
    private ScrollContainer columnPane;

    private DragGestureRecognizer dgr;
    private DragGestureHandler dragGestureHandler;


    private static final int WIDTH = 200;
    private static final int HEIGHT = 710;

    private DropTarget dropTarget;
    private DropHandler dropHandler;

    private BoardPanel columnParent;

    /**
     * General constructor for the KanbanColumn class.
     * @param columnTitle
     * @param role
     */
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
        titleLabel = new JLabel();
        ++id;

        initialiseColumn(columnTitle);
    }

    /**
     * Second constructor to account for the column needing a parent.
     * @param columnTitle
     * @param role
     * @param columnParent
     */
    public KanbanColumn(String columnTitle, ColumnRole role, BoardPanel columnParent) {
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
        this.columnParent = columnParent;
        titleLabel = new JLabel();
        ++id;

        initialiseColumn(columnTitle);
    }

    /**
     * Set up column layout and components
     * @param nameIn column name used
     */
    private void initialiseColumn(String nameIn) {

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        columnPane = new ScrollContainer();
        createColumnTitle(nameIn);
        add(titleLabel);
        add(columnPane);
        addButtons();

    }

    /**
     * Tells a column if something is being dragged above it.
     */
    @Override
    public void addNotify() {

        super.addNotify();
        dropHandler = new DropHandler(this);
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
        System.out.println("adding notify");
        if (dgr == null) {

            dragGestureHandler = new DragGestureHandler(this);
            dgr = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
                    this,
                    DnDConstants.ACTION_MOVE,
                    dragGestureHandler);

        }


    }


    /**
     * Tells a column if something has stopped being dragged above it.
     */
    @Override
    public void removeNotify() {
        System.out.println("removing notify");
        dropTarget.removeDropTargetListener(dropHandler);

        if (dgr != null) {

            dgr.removeDragGestureListener(dragGestureHandler);
            dragGestureHandler = null;

        }

        dgr = null;

        super.removeNotify();

    }



    /**
     * Initial set up of the column title label
     * @param nameIn initial name input
     */
    private void createColumnTitle(String nameIn) {

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.lightGray);
        titleLabel.setMaximumSize(new Dimension(200,30));
        titleLabel.setOpaque(true);
        titleLabel.setText(nameIn);
        titleLabel.setBackground(role.getColumnColour());
    }

    /**
     * Set up of the name label for the column. Used for updating after a column is edited.
     * @param nameIn name input
     */
    public void setColumnTitle(String nameIn) {

        titleLabel.setText(nameIn);
        titleLabel.setBackground(role.getColumnColour());

        try {
            Change change = new Change(Change.ChangeType.UPDATE, nameIn, this,
                    "title", nameIn);
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }

    }

    /**
     * Set up column role, from a choice of ColumnRoles
     * @param role
     */
    public void setRole(ColumnRole role) {

        BoardPanel board = getBoard();

        if (role == ColumnRole.IN_PROGRESS && (getColumnStoryPointsTotal()+board.getWIPcount() > board.getWIPlimit())){
            errorPane("Setting the role of this column to 'In Progress' would exceed your maximum WIP Limit, of "
                        + board.getWIPlimit() + ".",  "WIP Limit Reached");
            return;
        }


        this.role = role;

        // track change
        try {
            Change change = new Change(Change.ChangeType.UPDATE, getColumnTitle(), this,
                    "role", role.name());
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }
    }


    /**
     * Add button panel at the bottom of the column : edit and clear.
     */
    private void addButtons() {

        JPanel smallPanel = new JPanel();
        smallPanel.setMaximumSize(new Dimension(WIDTH, 30));

        // Edit button : opens an EditColumnFrame
        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit this column");
        editButton.addActionListener(e-> new UpdateColumnFrame(getBoard(), this).setVisible(true));
        editButton.setBackground(new java.awt.Color(110, 199, 233));
        editButton.setBorderPainted(false);

        // Clear button : removes all card from this column
        JButton clearButton = new JButton("Clear");
        clearButton.setToolTipText("Delete all cards from column?");
        clearButton.addActionListener(e->this.clearColumn());
        clearButton.setBackground(new java.awt.Color(250, 105, 128));
        clearButton.setBorderPainted(false);
        smallPanel.add(editButton);
        smallPanel.add(clearButton);

        add(smallPanel, BorderLayout.SOUTH);

    }

    /**
     * Add KanbanCardButton to the column.
     * The button is added to the main container in the column : columnPane (ScrollContainer).
     */
    public void addCard(KanbanCardButton card) {

        BoardPanel board = getBoard();

        // Check if IN_PROGRESS column, and make sure it doesn't exceed WIP limit
        if (role == ColumnRole.IN_PROGRESS &&
                (board.getWIPcount()+card.getCard().getStoryPoints() > board.getWIPlimit())) {
                    errorPane("The entered WIP limit is lower than the current WIP count : " +
                                    board.getWIPcount() + ".", "WIP Limit Too Low");
                    return;
        }
        if (role == ColumnRole.IN_PROGRESS) board.incrementWIPCount(card.getCard().getStoryPoints());

        cards.add(card);                                                // Add to ArrayList

        card.setAlignmentX(Component.CENTER_ALIGNMENT);                 // Add to columnPane
        columnPane.add(card);

        revalidate();
        repaint();

    }

    /**
     * Remove a selected KanbanCardButton from the column.
     * @param card to insert
     * @throws KanbanObjectNotFoundException
     */
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

    /**
     * Remove all cards form a column at once
     */
    private void clearColumn() {

        // If called on empty column
        if (cards.isEmpty()) {

            return;
        }

        cards.clear();
        columnPane.removeAll();
        revalidate();
        repaint();
    }


    /** GETTERS */

    public ColumnRole getRole() {
		return role;
	}

    /**
     * @return the addition total of all story points for this column (int)
     */
    private int getColumnStoryPointsTotal() {

        int total = 0;
        for (KanbanCardButton card : cards) total += card.getCard().getStoryPoints();
        return total;
    }

	public String getColumnTitle() {
        return titleLabel.getText();
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

    public BoardPanel getColumnParent() { return columnParent; }

    public void setColumnParent(BoardPanel columnParentBoard) { columnParent = columnParentBoard; }
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


}
