package view.frames;

import annotations.ClassAnnotation;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "08/12/2019"
)
/**
 * Help Frame displays information on how to use the application.
 * Accessible in the Kanban Menu Bar.
 */
public class HelpFrame extends PopUpFrames{


    /** Constructor - help frame set up */
    public HelpFrame() {
        super("Indigo Kanban Board - Help");
    }

    /**
     * Help text with instructions on how to use the Kanban board app in a JTextArea.
     * (Non editable)
     */
    @Override
    protected JTextArea createText() {

        JTextArea helpTextArea = new JTextArea();
        String helpString = "Welcome to the Indigo Kanban board app!\n\n" +
                "Use the editor panel on the right to add columns and cards to your board. " +
                "You can also remove any component\nyou'd like, or clear the whole board.\n" +
                "If you wish to modify some information you entered earlier, " +
                "you can use the buttons at the bottom of each column\nto either" +
                "edit its title and role, or clear all of its cards. Once you add a card to a column," +
                "clicking on it will allow you\nto view its description, and edit it. " +
                "To do so, change any information directly on the card, then click 'save changes'.\n\n" +
                "To exit the application, use the 'Exit application' button at the bottom of the editor panel.";

        helpTextArea.setText(helpString);
        helpTextArea.setLineWrap(true);
        helpTextArea.setMargin(new Insets(10,10,10,10));
        helpTextArea.setEditable(false);
        helpTextArea.setBackground(new java.awt.Color(204, 174, 240));
        return helpTextArea;
    }


}
