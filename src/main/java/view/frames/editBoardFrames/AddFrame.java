package view.frames.editBoardFrames;
import annotations.ClassAnnotation;
import controller.ColumnRole;
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
        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                ("Adding a new " + type + " to the board")));
    }

    protected JComboBox<String> createRolesList(){
        JComboBox<String> box = new JComboBox<>(ColumnRole.getRoles());
        return box;
    }



}
