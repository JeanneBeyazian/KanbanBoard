package controller;

import annotations.ClassAnnotation;

import javax.swing.*;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "08/12/2019",
        lastEdit = "08/12/2019"
)
/**
 * JOptionPanes displayed to the user on the GUI to warn them of errors, or misuses.
 */
public class OptionPanes {

    /** Custom error pane
     * @param errorMessage
     * @param errorTitle
     */
    public static void errorPane(String errorMessage, String errorTitle){
        JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
    }

    /** Response to an unknown command : error pane */
    public static void commandNotFoundError() {
        JOptionPane.showMessageDialog(null, "Command Not Found", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /** Response to a command ran on a missing component : info pane */
    public static void missingComponentError(String component){
        JOptionPane.showMessageDialog(null,"There are no " + component.toLowerCase() +
                        "s in your board! Please add a " + component.toLowerCase() + " to your board",
                component+ " Not Found", JOptionPane.INFORMATION_MESSAGE);
    }

}
