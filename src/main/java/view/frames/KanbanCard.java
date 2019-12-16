package view.frames;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import controller.exceptions.UnknownKanbanObjectException;
import model.Change;
import model.ChangeLog;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;
import view.frames.editBoardFrames.MoveCardFrame;

import javax.swing.*;
import java.awt.*;


@ClassAnnotation(
        classAuthors = "Nathan & Jeanne",
        classEditors = "Jeanne, Nathan, Petra",
        creationDate = "13/11/2019",
        lastEdit = "15/12/2019"
)
/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
public class KanbanCard extends JFrame {

	private static final long serialVersionUID = 1L;

	private static int id;                  // unique int id

    // Card frame components field
    private KanbanCardButton cardButton;
    private JTextField title;
    private JTextArea description;
    private JComboBox<Integer> storyPointsBox;

    // Internal object fields
    private String cardTitle;
    private String cardDescription;
    private Integer cardStoryPoints;


    /**
     * Constructor for KanbanCard
     * @param button        Associated KanbanCardButton
     * @param name          Card title, button name
     * @param description   Card description
     * @param storyPoints   Card slected story points
     */
    public KanbanCard(KanbanCardButton button, String name, String description, int storyPoints) {
        ++id;

        cardTitle = name;
        cardDescription = description;
        cardStoryPoints = storyPoints;

        // track change
        try {
            Change change = new Change(getBoard(), Change.ChangeType.ADD, name, this);
            ChangeLog.getInstance().addChange(change);
        } catch (UnknownKanbanObjectException u){
            System.out.println("Failed to log.");
            u.printStackTrace();
        }

        this.cardButton = button;
        add(makeContainerPanel(name,description,storyPoints));
        initialise();
    }

    /**
     * Set up the card frame
     */
    private void initialise() {

        setSize(700, 500);
        setTitle(title.getText());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    /**
     * Create a box layout panel with all the card components
     * @param titleIn           card title
     * @param descriptionIn     card description
     * @param storyPointsIn     card story points
     * @return panel
     */
    private JPanel makeContainerPanel(String titleIn, String descriptionIn, int storyPointsIn) {

        // Create Text areas
        title = new JTextField(titleIn);
        createStoryPointsBox(storyPointsIn);
        createDescriptionArea(descriptionIn);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(new java.awt.Color(142, 140, 250));

        containerPanel.add(makeTopPane());
        containerPanel.add(makeBottomPane());

        return containerPanel;

    }

    /**
     * Top pane contains title and description of card
     * @return topPane
     */
    private JPanel makeTopPane(){

        JPanel topPane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.PAGE_AXIS));
        topPane.add(title);
        topPane.add(Box.createRigidArea(new Dimension(0,5)));
        topPane.add(description);
        topPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        return topPane;

    }

    /**
     * Bottom pane contains story points and buttons
     * @return
     */
    private JPanel makeBottomPane() {

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.LINE_AXIS));
        bottomPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        bottomPane.add(Box.createHorizontalGlue());
        bottomPane.add(storyPointsBox);

        bottomPane.add(Box.createRigidArea(new Dimension(30, 0)));
        bottomPane.add(createMoveButton());

        bottomPane.add(Box.createRigidArea(new Dimension(30, 0)));
        bottomPane.add(createDeleteButton());

        bottomPane.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPane.add(createSaveButton());

        return bottomPane;
    }

    /**
     * Make a JTextArea for the card description
     * @param descriptionIn Initial description input
     */
    private void createDescriptionArea(String descriptionIn){
        description = new JTextArea(descriptionIn);
        description.setRows(20);
        description.setColumns(30);
    }

    /**
     * Make a JComboBox to store the story points
     * @param selectedPoints
     */
    private void createStoryPointsBox(int selectedPoints) {

        storyPointsBox = new JComboBox<Integer>();
        JLabel pointsLabel = new JLabel("Story Points:");
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        int max = 150;
        for (int i = 0; i <= max; i++) {
            storyPointsBox.addItem(i);

        }
        storyPointsBox.setSelectedIndex(selectedPoints);

    }

    /**
     * Create a button to delete the card frame as well as its assocaited kanbanCardButton
     * @return button to delete card
     */
    public JButton createDeleteButton() {

        JButton delete = new JButton("Delete card");
        delete.setBounds(500,500,5,5);

        delete.addActionListener(e-> {
            try {
                cardButton.getColumn().removeCard(cardButton);

                //log change
                try {
                    Change change = new Change(getBoard(), Change.ChangeType.REMOVE, cardTitle, this);
                    ChangeLog.getInstance().addChange(change);
                } catch (UnknownKanbanObjectException u){
                    System.out.println("Failed to log.");
                    u.printStackTrace();
                }

            } catch (KanbanObjectNotFoundException k) {
                delete.setEnabled(false); // disable button if not found
                delete.setText(delete.getText() + " (Error: Card not found)");
                System.out.println("Error: Card not found");
                k.printStackTrace();
            }
        });
        return delete;
    }

    /**
     * @return button for saving changes on a card
     */
    public JButton createSaveButton() {

        JButton save = new JButton("Save changes");
        save.setBounds(500,500,5,5);
        save.addActionListener(e->update());
        return save;
    }

    /**
     * Create JButton for moving a card from a column to another
     * @return moveButton
     */
    public JButton createMoveButton() {

        JButton move = new JButton("Change column");
        move.setBounds(500,500,5,5);
        move.addActionListener(e->new MoveCardFrame(getBoard(),cardButton).setVisible(true));
        return move;
    }

    /**
     * Method which decides whether a card has been updated on save.
     * Checks for changes such as title, description, or story points change.
     */
    public void update() {

        // Get input values
        String newTitle = title.getText();
        String newDescription = description.getText();
        int newPoint = getStoryPoints();

        // Update
        setTitle(newTitle);
        description.setText(newDescription);
        cardButton.setStoryPoints(String.valueOf(newPoint));
        cardButton.setTitle(newTitle);

        // Only log if values have changed
        // track change
        if(!cardTitle.equals(newTitle)) {
            try {
                Change change = new Change(getBoard(), Change.ChangeType.UPDATE, cardTitle, this,
                        "title", newTitle);
                ChangeLog.getInstance().addChange(change);
            } catch (UnknownKanbanObjectException u) {
                System.out.println("Failed to log.");
                u.printStackTrace();
            }
        }

        if(!cardDescription.equals(newDescription)) {
            try {
                Change change = new Change(getBoard(), Change.ChangeType.UPDATE, cardTitle, this,
                        "description", newDescription);
                ChangeLog.getInstance().addChange(change);
            } catch (UnknownKanbanObjectException u) {
                System.out.println("Failed to log.");
                u.printStackTrace();
            }
        }


        if(cardStoryPoints != newPoint) {
            try {
                Change change = new Change(getBoard(), Change.ChangeType.UPDATE, cardTitle, this,
                        "story points", String.valueOf(newPoint));
                ChangeLog.getInstance().addChange(change);
            } catch (UnknownKanbanObjectException u) {
                System.out.println("Failed to log.");
                u.printStackTrace();
            }
        }

        // Update internal object fields
        cardTitle = newTitle;
        cardDescription = newDescription;
        cardStoryPoints = newPoint;

        dispose();

    }

    /**
     * @return JTextField for card title choosing
     */
    public JTextField getCardTitleField() {
        return title;
    }

    /**
     * @return JTextArea for card description choosing
     */
    public JTextArea getDescription() {
        return description;
    }

    /**
     * @return JComboBox for story points choosing
     */
    public JComboBox<Integer> getStoryPointsBox() {
        return storyPointsBox;
    }

    /**
     * @return card title (String)
     */
    public String getCardTitle(){
        return String.valueOf(title.getText());
    }

    /**
     * @return card description (String)
     */
    public String getCardDescription(){
        return String.valueOf(description.getText());
    }

    /**
     * @return card points (Integer)
     */
    public int getStoryPoints() {
        return Integer.valueOf(storyPointsBox.getSelectedItem().toString());
    }


    /**
     * @return card unique id (int)
     */
    public int getId(){
        return id;
    }


    /**
     * @return boardPanel for the card's column
     */
    public BoardPanel getBoard(){
        return cardButton.getColumn().getBoard();
    }

}
