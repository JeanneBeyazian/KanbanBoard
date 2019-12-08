package view.frames;

import annotations.ClassAnnotation;
import controller.exceptions.KanbanObjectNotFoundException;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@ClassAnnotation(
        classAuthors = {"Jeanne & Nathan"},
        creationDate = "13/11/2019",
        lastEdit = "05/11/2019"
)
/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
public class KanbanCard extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int id;
    private KanbanCardButton cardButton;
    private JTextArea title;
    private JTextArea description;
    private JTextArea storyPoints;
    private static String des;
    private static int point;
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
        setSize(500, 500);
        setTitle(title.getText());
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
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
        des = descriptionIn;
        //point = String.valueOf(storyPointsIn);
        point = storyPointsIn;

        title = new JTextArea(titleIn);
        //storyPoints = new JTextArea(point);
        storyPointsBox = new JComboBox();
        JLabel pointsLabel = new JLabel("Story Points:");
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        int max = 150;
        for (int i = 0; i <= max; i++) {
            storyPointsBox.addItem(i);

        }
        storyPointsBox.setSelectedIndex(point);

        createDescriptionArea(des);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLUE);
        //panel.setSize(600,550);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(description);
        panel.add(pointsLabel);
        panel.add(storyPointsBox);
        panel.add(createDeleteButton());
        panel.add(createSaveButton());

        return panel;

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
     * @return button to delete card
     */
    public JButton createDeleteButton() {
        JButton delete = new JButton("delete");
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

    public JButton createSaveButton() {
        JButton save = new JButton("save");
        save.setBounds(500,500,5,5);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newTitle = title.getText();
                String newDes = description.getText();
                //String newPoint = storyPoints.getText();
                int newPoint = (int) storyPointsBox.getSelectedItem();
                setTitle(newTitle);
                setDescription(newDes);
                setPoint(newPoint);
                cardButton.setTitle(newTitle);
                //        cardButton.setText(newTitle);
            }
        });
        return save;
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
        return Integer.valueOf(storyPoints.getText());
    }


    /**
     * @return card unique id (int)
     */
    public int getId(){
        return id;
    }


    public void setDescription(String desIn) {
        des = desIn;
    }

    public void setPoint(int pointIn) {
        point = pointIn;
    }



    /**
     * @return boardPanel for the card's column
     */
    public BoardPanel getBoard(){
        return cardButton.getColumn().getBoard();
    }

}
