package view.frames.popUpFrames;

import annotations.ClassAnnotation;
import controller.Load;
import view.KanbanBoard;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;
import view.frames.OpenFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "09/12/2019",
        lastEdit = "10/12/2019"
)
public class LoadWarningFrame extends PopUpFrames {

    /**
     * Constructor - help frame set up.
     */
    public LoadWarningFrame() {

        super("Loading Board Warning");
        buttonPanel.add(createSubmitButton());

    }

    /**
     * Help text with instructions on how to use the Kanban board app in a JTextArea.
     * (Non editable)
     */
    @Override
    protected JTextArea createText()  {

        JTextArea warningText = new JTextArea();
        String text = "WARNING.\n\nAre you sure you want to load an existing board?\n" +
                "If you haven't saved your current progress, the board you're currently working on" +
                " will be erased.\nThis action cannot be undone, and the data cannot be retrieved.\n\n"+
                "Do you want to proceed with this action?";

        warningText.setText(text);
        warningText.setMargin(new Insets(10,10,10,10));
        warningText.setEditable(false);
        return warningText;
    }


    private JButton createSubmitButton() {

        JButton proceed = new JButton("Proceed");
        proceed.addActionListener(e-> new OpenFrame().setVisible(true));
        proceed.setBorderPainted(false);

        return proceed;
    }




}
