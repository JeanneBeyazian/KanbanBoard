package view;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.*;

/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
@ClassAnnotation(
        classAuthors = {"Jeanne, Nathan"},
        creationDate = "13/11/2019",
        lastEdit = "26/11/2019"
)
public class KanbanCard extends JFrame {

    private static int id;
    private JTextArea title;
    private JTextArea description;
    private JTextArea storyPoints;


    public KanbanCard(String name, String description, int storyPoints) {
        ++id;
        add(makeContainerPanel(name,description,storyPoints));
        initialise();

    }

    private void initialise() {
        setSize(200, 150);
        setTitle(title.getText());
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }

    private JPanel makeContainerPanel(String titleIn, String descriptionIn, int storyPointsIn) {

        // Create Text areas
        title = new JTextArea(titleIn);
        storyPoints = new JTextArea(String.valueOf(storyPointsIn));
        createDescriptionArea(descriptionIn);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLUE);
        //panel.setSize(600,550);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(description);
        panel.add(storyPoints);
        panel.add(createDeleteButton());

        return panel;

    }

    private void createDescriptionArea(String descriptionIn){
        description = new JTextArea(descriptionIn);
        description.setRows(20);
        description.setColumns(30);
    }

    private JButton createDeleteButton() {
        JButton delete = new JButton("delete");
        delete.setBounds(500,500,5,5);
        return delete;
    }

    public void setDescription(String newDes) {
        description.setText(newDes);
    }

    public void setPoint(int newPoint) {
       storyPoints.setText(String.valueOf(newPoint));
    }

    public int getId(){
        return id;
    }

}
