package view;

import annotations.ClassAnnotation;

import javax.swing.*;

/**
 * This class is responsible for the creation of kanban cards.
 * Each class contains an id, a title, a description and story points.
 * Cards are inserted in to KanbanColumns.
 */
@ClassAnnotation(
        classAuthors = {"Jeanne, Nathan"},
        creationDate = "13/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanCard extends JPanel {

    // TODO : Give a unique ID to each card
    // TODO : Fix the group layout - or change to a new type of layout

    private int id;
    private String description;
    private int storyPoints;

    public KanbanCard(String name, String description, int storyPoints) {

        /**JLabel title = new JLabel(name);
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
        );*/

        add(new JLabel("This is a card"));

    }


}
