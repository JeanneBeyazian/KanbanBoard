package view.containers;

import annotations.ClassAnnotation;
import controller.ActivityType;
import view.boardComponents.ActivityButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "22/11/2019"
)
/**
 * Log Panel is a tabbed pane made of two JPanels for activity log and versions history.
 * The Log Panel is located inside the Editor Panel.
 */
public class LogPanel extends JTabbedPane {

    private static final int LOG_WIDTH = 100;
    private static final int LOG_HEIGHT = 330;

    private ScrollContainer activityLog;

    public LogPanel(){
        initialiseLogPanel();
    }

    /**
     * Initialise the panel and create its components : activityLog and versionsLog.
     */
    public void initialiseLogPanel() {

        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setPreferredSize(new Dimension(LOG_WIDTH,LOG_HEIGHT));
        setOpaque(false);

        makeActivityLog();

        addTab("Activity log", null, activityLog, "See recent changes");
        addTab("Recent files", null, makeVersionsLog(), "See history");
    }

    /** TESTING PURPOSE */
    private void makeActivityLog(){

        activityLog = new ScrollContainer();

        ActivityButton button1 = new ActivityButton(ActivityType.BOARD_RESET);
        ActivityButton button2 = new ActivityButton(ActivityType.COLUMN_ADD);
        ActivityButton button3 = new ActivityButton(ActivityType.CARD_REMOVE);

        ScrollContainer activityScroll = new ScrollContainer();
        activityScroll.add(button1);
        activityScroll.add(button2);
        activityScroll.add(button3);
    }

    /**
     * Create the versions history panel - second panel of the tabbed panels.
     * @return panel with button to access history
     */
    private JPanel makeVersionsLog() {

        JPanel panel = new JPanel();
        JButton seeVersions = new JButton("See Full Board history");
        panel.setBorder(new EmptyBorder(50, 10, 50, 10));
        panel.add(seeVersions);
        return panel;
    }

}
