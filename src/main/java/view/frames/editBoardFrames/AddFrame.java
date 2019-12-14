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
        lastEdit = "12/12/2019"
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
        titleLabel.setName("titleLabel");

        titleField = new JTextField(20);
        titleField.setName("titleField");

        setUpFrame(type);
    }

    /** Set up of the frame : add titleField and titleLabel */
    protected void setUpFrame(String type) {
        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Adding a new " + type + " to the board")));
    }

    protected JComboBox<String> createRolesList(){
        JComboBox<String> box = new JComboBox<>(ColumnRole.getRoles());
        box.setName("columnRolesBox");
        return box;
    }



}
