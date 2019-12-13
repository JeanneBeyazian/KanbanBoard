package view.containers;

import annotations.ClassAnnotation;
import model.Change;
import model.ChangeLog;
import view.boardComponents.ActivityButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@ClassAnnotation(
        classAuthors = {"Jeanne", "Petra"},
        creationDate = "22/11/2019",
        lastEdit = "08/12/2019"
)
/**
 * Log Panel is a tabbed pane made of two JPanels for activity log and versions history.
 * The Log Panel is located inside the Editor Panel.
 */
public class LogPanel extends JPanel {

    private static final int LOG_WIDTH = 100;
    private static final int LOG_HEIGHT = 330;

    private ScrollContainer activityLog;

    public LogPanel(){
        ChangeLog log = ChangeLog.getInstance();
        initialiseLogPanel();

        log.addListener(this); // add this panel to ChangeLog listener list
        updateLog();
    }

    /**
     * Initialise the panel and create its components : activityLog and versionsLog.
     */
    public void initialiseLogPanel() {

        setPreferredSize(new Dimension(LOG_WIDTH,LOG_HEIGHT));
        setOpaque(false);
        activityLog = new ScrollContainer();
        add(makeChangeLog());
    }


    private JPanel makeChangeLog(){

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(activityLog);
        return panel;
    }

    /**
     * Triggered by ChangeLog (observer design pattern).
     * Update the list of log entries.
     */
    public void updateLog() {

        ChangeLog log = ChangeLog.getInstance();
        String logEntry = "";
        Change c = log.getLastChange();
        try {
            logEntry = c.formatAsString();
        } catch (Exception e) {
            System.out.println("Failed to convert log entry");
        }

        ActivityButton button = new ActivityButton(logEntry, c.getChangeType());
        activityLog.add(button);

        revalidate();
        repaint();


    }

}
