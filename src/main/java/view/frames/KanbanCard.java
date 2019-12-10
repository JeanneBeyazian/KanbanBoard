package view.frames;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.frames.editBoardFrames.MoveCardFrame;

import javax.swing.*;
import java.awt.*;


@ClassAnnotation(
        classAuthors = {"Jeanne & Nathan"},
        creationDate = "13/11/2019",
        lastEdit = "08/11/2019"
)
/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
public class KanbanCard extends JFrame {

	private static final long serialVersionUID = 1L;

	private static int id;

    private KanbanCardButton cardButton;
    private JTextField title;
    private JTextArea description;
    private JTextArea storyPoints;

    private JComboBox<Integer> storyPointsBox;

    public KanbanCard(KanbanCardButton button, String name, String description, int storyPoints) {
        ++id;
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

    private JPanel makeTopPane(){

        JPanel topPane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.PAGE_AXIS));
        topPane.add(title);
        topPane.add(Box.createRigidArea(new Dimension(0,5)));
        topPane.add(description);
        topPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        return topPane;

    }

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

    private void createStoryPointsBox(int selectedPoints) {

        storyPointsBox = new JComboBox();

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
     * @return button to delete card
     */
    public JButton createDeleteButton() {
        JButton delete = new JButton("Delete card");
        delete.setBounds(500,500,5,5);
        //delete.addActionListener(e->cardButton.getColumn().removeCard(cardButton, null));
        delete.addActionListener(e-> {
            try {
                cardButton.getColumn().removeCard(cardButton);
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
     * @return button for saving changes
     */
    public JButton createSaveButton() {

        JButton save = new JButton("Save changes");
        save.setBounds(500,500,5,5);
        save.addActionListener(e->update());
        return save;
    }

    public JButton createMoveButton() {

        JButton move = new JButton("Change column");
        move.setBounds(500,500,5,5);
        move.addActionListener(e->new MoveCardFrame(getBoard(),cardButton).setVisible(true));
        return move;
    }

    private void update() {
        // Get input values
        String newTitle = title.getText();
        String newDescription = description.getText();
        int newPoint = (int) storyPointsBox.getSelectedItem();

        // Update
        setTitle(newTitle);
        description.setText(newDescription);
        storyPointsBox.setSelectedItem(newPoint);
        cardButton.setTitle(newTitle);

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
        return (int)storyPointsBox.getSelectedItem();
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
