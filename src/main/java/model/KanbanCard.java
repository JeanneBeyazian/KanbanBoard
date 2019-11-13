package model;

import javax.swing.*;

/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
public class KanbanCard extends JPanel {

    private int id;
    private String description;
    private int storyPoints;

    public KanbanCard(String name, String description, int storyPoints) {

        JLabel title = new JLabel(name);
        JLabel info = new JLabel(description);
        JLabel points = new JLabel(String.valueOf(storyPoints));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(info)
                        .addComponent(points)
        );
        
    }


}
