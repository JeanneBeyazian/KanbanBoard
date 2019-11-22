package view;

import controller.ActivityType;

import javax.swing.*;

public class ActivityPane extends JPanel {

    private JLabel activityName;

    public ActivityPane(ActivityType activityType) {

        activityName = new JLabel(activityType.getDescription());

        if (activityType.isAddition() == true ) {
            setBackground(new java.awt.Color(92, 151, 100, 255));
        }
        else {
            setBackground(new java.awt.Color(145, 65, 53, 255));
        }
    }

}
