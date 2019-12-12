package view.boardComponents;

import annotations.ClassAnnotation;
import model.Change;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "12/12/2019"
)

/**
 * Activity buttons are added into the log panel. They describe the last change made on the board.
 */
public class ActivityButton extends JPanel implements ActionListener {


	private static final long serialVersionUID = 1L;
    private JButton activityButton;
    private static final int ACTIVITY_BUTTON_WIDTH = 280;
    private static final int ACTIVITY_BUTTON_HEIGHT = 40;

    public ActivityButton(String str, Change.ChangeType changeType) {

        activityButton = new JButton(str);
        initialiseButton();
        this.add(activityButton);
        setBackground(changeType);
        setMaximumSize(new Dimension(ACTIVITY_BUTTON_WIDTH,ACTIVITY_BUTTON_HEIGHT));

    }

    private void initialiseButton() {

        activityButton.setBorderPainted(false);
        activityButton.setOpaque(false);
        activityButton.setPreferredSize(new Dimension(ACTIVITY_BUTTON_WIDTH, ACTIVITY_BUTTON_HEIGHT));
        activityButton.setContentAreaFilled(false);
        activityButton.setHorizontalAlignment(SwingConstants.LEFT);
        activityButton.setToolTipText(activityButton.getText());
        activityButton.setFont(new Font("Arial", Font.ITALIC, 10));

    }


    private void setBackground(Change.ChangeType changeType) {

        if (changeType == Change.ChangeType.ADD) {
            setBackground(new java.awt.Color(130, 191, 150, 255));
        }
        else if (changeType == Change.ChangeType.REMOVE) {
            setBackground(new java.awt.Color(168, 47, 38, 235));
            //activityButton.setForeground(Color.lightGray);
        }
        else if (changeType == Change.ChangeType.UPDATE) {
            setBackground(new java.awt.Color(112, 77, 163, 235));
            //activityButton.setForeground(Color.lightGray);
        }
        else if (changeType== Change.ChangeType.MOVE) {
            setBackground(new java.awt.Color(46, 129, 164, 235));
            //activityButton.setForeground(Color.lightGray);
        }
        else {
            setBackground(new java.awt.Color(67, 65, 68, 235));
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Once you click on log panel, go back to one state of the board
    }


}
