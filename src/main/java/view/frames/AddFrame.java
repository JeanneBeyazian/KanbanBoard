package view.frames;
import annotations.ClassAnnotation;
import view.boardComponents.BoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "26/11/2019",
        lastEdit = "05/11/2019"
)
/**
 * Super class for addition of components on the board (columns or cards).
 * Extends EditorFrame.
 */
abstract public class AddFrame extends EditorFrame implements ActionListener {

    protected JTextField titleField;
    protected JLabel titleLabel;

    public AddFrame(String type, BoardPanel currentPanel) {
        super(currentPanel);
        this.currentPanel = currentPanel;
        titleLabel = new JLabel("Enter a title:");
        titleField = new JTextField(20);
        setUpFrame(type);
    }

    /** Set up of the frame : add titleField and titleLabel */
    protected void setUpFrame(String type ) {
        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Adding a new " + type + " to the board")));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        // add title and title textField to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(titleLabel, constraints);

        constraints.gridx = 1;
        container.add(titleField, constraints);

        pack();
    }



}
