package view;

import javax.swing.*;

/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
public class KanbanCard extends JFrame {

    // TODO : Give a unique ID to each card
    // TODO : Fix the group layout - or change to a new type of layout

    private static int id;
    private String description;
    private int storyPoints;
    private String title;

    public KanbanCard(String name, String description, int storyPoints) {

        JLabel titleLabel = new JLabel(name);
        JLabel info = new JLabel(description);
        JLabel points = new JLabel(String.valueOf(storyPoints));
/**
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(info)
                        .addComponent(points)
        );*/

        add(new JLabel("This is a card"));

    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setDescription(String newDes) {
        description = newDes;
    }

    public void setPoint(int newPoint) {
        storyPoints = newPoint;
    }
}
