package view;

import controller.ActivityType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogPanel extends JTabbedPane {

    private static final int LOG_WIDTH = 100;
    private static final int LOG_HEIGHT = 300;

    private ScrollContainer activityLog;
    private ScrollContainer versionsLog;

    public LogPanel(){
        initialiseLogPane();
    }

    public void initialiseLogPane() {

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setPreferredSize(new Dimension(LOG_WIDTH,LOG_HEIGHT));
        activityLog = new ScrollContainer();
        versionsLog = new ScrollContainer();

        addTab("Activity log", null, makeActivityLog(), "See recent changes");
        //SHOULD BE addTab("Activity log", null, activityLog, "See recent changes");
        //activity.setMnemonicAt(0, KeyEvent.VK_1);

        addTab("Recent files", null, versionsLog, "See history");
        //activity.setMnemonicAt(1, KeyEvent.VK_2);
    }

    /** TESTING PURPOSE */
    private JScrollPane makeActivityLog(){

        ActivityButton button1 = new ActivityButton(ActivityType.BOARD_RESET);
        ActivityButton button2 = new ActivityButton(ActivityType.COLUMN_ADD);
        ActivityButton button3 = new ActivityButton(ActivityType.CARD_REMOVE);

        ScrollContainer activityScroll = new ScrollContainer();
        activityScroll.add(button1);
        activityScroll.add(button2);

        return activityScroll;
    }

    private JPanel makeVersionsLog() {
        JPanel panel = new JPanel();
        JButton seeVersions = new JButton("See Full Board history");
        panel.setBorder(new EmptyBorder(50, 10, 50, 10));
        panel.add(seeVersions);
        return panel;
    }

}
