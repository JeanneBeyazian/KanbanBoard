package view.boardComponents;

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
public class ActivityButton extends JPanel {


	private static final long serialVersionUID = 1L;
    private JButton activityButton;
    private static final int ACTIVITY_BUTTON_WIDTH = 240;
    private static final int ACTIVITY_BUTTON_HEIGHT = 45;

    public ActivityButton(ActivityType activityType) {

        activityButton = new JButton(activityType.getDescription());
        setUpButton();

        // If element has been added to board
        if (activityType.isAddition() == true ) setBackground(new java.awt.Color(66, 133, 169, 255));
        else {
            setBackground(new java.awt.Color(3, 21, 57, 235));
            activityButton.setForeground(Color.lightGray);
        }
        setMaximumSize(new Dimension(ACTIVITY_BUTTON_WIDTH,ACTIVITY_BUTTON_HEIGHT));

        this.add(activityButton);

    }

    private void setUpButton(){
        activityButton.setBorderPainted(false);
        activityButton.setOpaque(false);
        activityButton.setPreferredSize(new Dimension(ACTIVITY_BUTTON_WIDTH, ACTIVITY_BUTTON_HEIGHT));
        activityButton.setContentAreaFilled(false);
    }

}
