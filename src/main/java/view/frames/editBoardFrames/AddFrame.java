package view.frames.editBoardFrames;
import annotations.ClassAnnotation;
import controller.ColumnRole;
import view.boardComponents.BoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "28/11/2019",
        lastEdit = "15/12/2019"
)
/**
 * Super class for addition of components on the board (columns or cards).
 * Extends EditorFrame.
 */
abstract public class AddFrame extends EditorFrame implements ActionListener {

    // Frame specific components
    protected JTextField titleField;
    protected JLabel titleLabel;


    public AddFrame(String type, BoardPanel currentPanel) {

        super(currentPanel);
        this.currentPanel = currentPanel;

        setUpFrame(type);
    }

    /** Set up of the frame : make titleField and titleLabel */
    protected void setUpFrame(String type) {
        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Adding a new " + type + " to the board")));

        titleLabel = new JLabel("Enter a title:");
        titleLabel.setName("titleLabel");

        titleField = new JTextField(20);
        titleField.setName("titleField");

    }

    /**
     * Create a combo box containing all the column roles available
     * @return
     */
    protected JComboBox<String> createRolesList() {

        JComboBox<String> box = new JComboBox<>(ColumnRole.getRoles());
        box.setName("columnRolesBox");
        return box;

    }



}
