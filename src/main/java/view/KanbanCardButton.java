package view;

import javax.swing.*;

public class KanbanCardButton extends JButton {
    private String title;
    private String description;
    private int storyPoints;

    public KanbanCardButton(String name, String description, int storyPoints) {
        super(name);

    }
}
