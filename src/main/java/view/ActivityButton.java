package view;

import annotations.ClassAnnotation;
import controller.ActivityType;

import javax.swing.*;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "22/11/2019"
)

/**
 * Activity buttons are added into the log panel. They describe the last change made on the board.
 * If something has been added, the button appears green.
 * If something has been removed, the button appears blue.
 */
public class ActivityButton extends JButton {

    private static final int ACTIVITY_BUTTON_WIDTH = 222;
    private static final int ACTIVITY_BUTTON_HEIGHT = 30;

    public ActivityButton(ActivityType activityType) {

        setText(activityType.getDescription());
        setBorderPainted(false);
        setPreferredSize(new Dimension(ACTIVITY_BUTTON_WIDTH, ACTIVITY_BUTTON_HEIGHT));

        // If element has been added to board
        if (activityType.isAddition() == true ) {
            setBackground(new java.awt.Color(107, 169, 115, 255));
        }
        else {
            setBackground(new java.awt.Color(70, 123, 139, 235));
        }

    }

}
