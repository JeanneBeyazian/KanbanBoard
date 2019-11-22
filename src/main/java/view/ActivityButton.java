package view;

import controller.ActivityType;

import javax.swing.*;
import java.awt.*;

public class ActivityButton extends JButton {

    private static final int ACTIVITY_BUTTON_WIDTH = 222;
    private static final int ACTIVITY_BUTTON_HEIGHT = 30;

    public ActivityButton(ActivityType activityType) {

        setText(activityType.getDescription());
        setPreferredSize(new Dimension(ACTIVITY_BUTTON_WIDTH, ACTIVITY_BUTTON_HEIGHT));

        if (activityType.isAddition() == true ) {
            setBackground(new java.awt.Color(92, 151, 100, 255));
        }
        else {
            setBackground(new java.awt.Color(145, 65, 53, 255));
        }

    }

}
